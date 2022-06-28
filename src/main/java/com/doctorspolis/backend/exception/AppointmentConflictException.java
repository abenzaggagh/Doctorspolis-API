package com.doctorspolis.backend.exception;

import com.doctorspolis.backend.commun.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppointmentConflictException extends AbstractException {

    public AppointmentConflictException(String message, String details, Object... arguments) {
        super(message, details, arguments);
    }

}
