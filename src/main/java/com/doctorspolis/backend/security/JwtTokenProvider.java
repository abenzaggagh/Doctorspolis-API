package com.doctorspolis.backend.security;

import com.doctorspolis.backend.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.service.UserService;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private String secretKey = "serrrrrrrdfqhdfgqdfsgvqdsgvqzedsgfefahjzrttT5EZAR65E6434YErrrrrrrcret";

    private final long accessExpirationPeriod = 900000;

    private final long refreshExpirationPeriod = 604800000;

    private final UserService userService;

    @Autowired
    public JwtTokenProvider(UserService userService) {
        this.userService = userService;
    }

    public String createAccessToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpirationPeriod);

        // TODO: Replace the deprecated signWith method
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity).signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAuthentication(String token) {
        User user = this.userService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(DoctorspolisConstants.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(DoctorspolisConstants.BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new ResourceNotFoundException("Expired or invalid JWT token");
        }
    }

}
