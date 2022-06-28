package com.doctorspolis.backend.controller.exception;

import com.doctorspolis.backend.commun.AbstractException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppointmentCancellationProhibitedException extends AbstractException {

    public AppointmentCancellationProhibitedException(String message) {
        super(message);
    }

    public AppointmentCancellationProhibitedException(String messageFormat, Object... arguments) {
        super(messageFormat, arguments);
    }

}
