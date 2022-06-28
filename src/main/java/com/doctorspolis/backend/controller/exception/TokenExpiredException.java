package com.doctorspolis.backend.controller.exception;

import com.doctorspolis.backend.commun.AbstractException;

public class TokenExpiredException extends AbstractException {

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String messageFormat, Object... args) {
        super(messageFormat, args);
    }

}
