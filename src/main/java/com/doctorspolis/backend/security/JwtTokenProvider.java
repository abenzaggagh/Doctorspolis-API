package com.doctorspolis.backend.security;

import com.doctorspolis.backend.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.service.UserService;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    protected Key key;

    private final UserService userService;

    @Value("${doctorspolis.jwt.secret.key}")
    private String secretKey;

    @Value("${doctorspolis.jwt.access.expiration.period}")
    private int accessExpirationPeriod;

    @Value("${doctorspolis.jwt.refresh.expiration.period}")
    private int refreshExpirationPeriod;

    @Autowired
    public JwtTokenProvider(UserService userService) {
        this.userService = userService;
    }

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

    public Authentication getAuthentication(String token) {
        User user = this.userService.loadUserByUsername(getUsername(token));

        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(DoctorspolisConstants.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(DoctorspolisConstants.BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isAccessTokenValid(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new ResourceNotFoundException("Expired or invalid JWT token");
        }
    }

}
