package com.doctorspolis.backend.configuration;

import com.doctorspolis.backend.security.JwtConfigurer;
import com.doctorspolis.backend.security.JwtEntryPoint;
import com.doctorspolis.backend.security.JwtFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;
    // private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfiguration(JwtConfigurer jwtConfigurer
                                 /* JwtTokenProvider jwtTokenProvider */) {
        this.jwtConfigurer = jwtConfigurer;
        // this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public JwtEntryPoint jwtEntryPointBean() {
        return new JwtEntryPoint();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // TODO: Learn more about Spring Interceptors and Filters
                // TODO: Send a Error DTO when the Token is expired
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**/sign-in").permitAll()
                .antMatchers("/**/sign-up").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new JwtFailureHandler())
                .authenticationEntryPoint(jwtEntryPointBean())
                .and()
                .apply(jwtConfigurer);
    }

}
