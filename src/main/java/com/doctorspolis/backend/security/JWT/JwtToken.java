package com.doctorspolis.backend.security.JWT;

import com.doctorspolis.backend.utility.TokenInterface;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtToken implements TokenInterface, Serializable {

    @Override
    public String generateAccessToken(String username) {
        return null;
    }

    @Override
    public String generateRefreshToken(String username) {
        return null;
    }

    @Override
    public Boolean hasTokenExpired(String token) {
        return null;
    }

    @Override
    public Boolean validateToken(String token, String username) {
        return null;
    }

}