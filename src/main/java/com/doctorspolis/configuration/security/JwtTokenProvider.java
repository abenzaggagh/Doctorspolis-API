package com.doctorspolis.configuration.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

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
