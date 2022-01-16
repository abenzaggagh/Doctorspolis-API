package com.doctorspolis.backend.utility;

public interface TokenInterface {

    String generateAccessToken(String username);

    String generateRefreshToken(String username);

    Boolean hasTokenExpired(String token);

    Boolean validateToken(String token, String username);

}
