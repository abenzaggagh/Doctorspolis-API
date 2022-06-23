package com.doctorspolis.backend.exception.handler;

import com.doctorspolis.backend.exception.DoctorNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DoctorExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO: Create a custom Error Message DTO
    @ExceptionHandler(DoctorNotFoundException.class)
    protected ResponseEntity<String> handleEntityNotFound(DoctorNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
       return new ResponseEntity<>(ex.getMessage(), status);
    }

}
