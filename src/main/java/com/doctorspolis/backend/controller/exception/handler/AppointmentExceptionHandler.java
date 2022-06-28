package com.doctorspolis.backend.controller.exception.handler;

import com.doctorspolis.backend.commun.AbstractException;
import com.doctorspolis.backend.commun.AbstractExceptionHandler;
import com.doctorspolis.backend.controller.exception.AppointmentCancellationProhibitedException;
import com.doctorspolis.backend.controller.exception.AppointmentConflictException;
import com.doctorspolis.backend.model.DTO.ErrorDTO;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppointmentExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler({ AppointmentConflictException.class,
                        AppointmentCancellationProhibitedException.class })
    protected ResponseEntity<ErrorDTO> handleConflictAppointments(AbstractException exception) {
        return new ResponseEntity<>(ErrorDTO
                .builder()
                .error("10001")
                .status("400")
                .message(loadMessage(exception.getMessage()))
                .details(loadMessage(exception.getMessage() + ".details", exception.getArguments()))
                .build(),
                HttpStatus.BAD_REQUEST);
    }

}
