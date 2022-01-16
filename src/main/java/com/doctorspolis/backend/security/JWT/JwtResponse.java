package com.doctorspolis.backend.security.JWT;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtResponse implements Serializable {

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

}
