package com.doctorspolis.backend.configuration;

import com.doctorspolis.backend.repository.UserRepository;
import com.doctorspolis.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EntityScan("com.doctorspolis.backend.model")
@EnableJpaRepositories("com.doctorspolis.backend.repository")
public class DatabaseConfiguration implements CommandLineRunner  {

    private final UserRepository userRepository;

    // private final PatientRepository patientRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public DatabaseConfiguration(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void run(String... args) {
        /*this.userRepository.save(User.builder()
                .username("abenzaggagh")
                .password(this.passwordEncoder.encode("password"))
                .refreshToken(jwtTokenProvider.createRefreshToken())
                .enabled(true)
                .build());*/
        // this.patientRepository.save(Patient.builder()..build())
    }

}