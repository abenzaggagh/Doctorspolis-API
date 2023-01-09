package com.doctorspolis.backend.controller.exception.handler;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DoctorExceptionHandler {

    // TODO: Create a custom Error Message DTO
    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<String> handleEntityNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
