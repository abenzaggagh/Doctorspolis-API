package com.doctorspolis.backend.exception;

import com.doctorspolis.backend.commun.AbstractException;

public class ResourceNotFoundException extends AbstractException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String messageFormat, Object... args) {
        super(messageFormat, args);
    }

}
