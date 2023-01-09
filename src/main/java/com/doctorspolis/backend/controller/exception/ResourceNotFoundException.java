package com.doctorspolis.backend.controller.exception;

import com.doctorspolis.backend.utility.commun.AbstractException;

public class ResourceNotFoundException extends AbstractException {

    public ResourceNotFoundException(Object message) {
        super(String.valueOf(message));
    }

    public ResourceNotFoundException(String messageFormat, String... args) {
        super(messageFormat, args);
    }

}
