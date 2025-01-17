package com.doctorspolis.configuration.security;

import com.doctorspolis.controller.exception.AuthenticationException;
import com.doctorspolis.model.data.User;
import com.doctorspolis.model.dto.authentication.AuthenticationResponseDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

import static com.doctorspolis.controller.exception.AuthenticationExceptionEnum.*;
import static com.doctorspolis.utility.DoctorspolisConstants.AUTHORIZATION;
import static com.doctorspolis.utility.DoctorspolisConstants.BEARER;

@RequiredArgsConstructor

@Component
public class JwtTokenProvider {

    protected Key key;

    @Value("${doctorspolis.jwt.secret.key}")
    private String secretKey;

    @Getter
    @Value("${doctorspolis.jwt.expiration.period.access}")
    private Integer accessExpirationPeriod;

    @Value("${doctorspolis.jwt.expiration.period.refresh}")
    private Integer refreshExpirationPeriod;

    @PostConstruct
    protected void initSecretKey() {
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }


    public String createAccessToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpirationPeriod);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshExpirationPeriod);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    /***
     * Method returns true if the token is valid, false otherwise
     * @param token
     * @return
     * @throws ExpiredJwtException
     */
    public boolean isTokenValid(String token) throws ExpiredJwtException {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        return !claims.getBody().getExpiration().before(new Date());
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }


}
