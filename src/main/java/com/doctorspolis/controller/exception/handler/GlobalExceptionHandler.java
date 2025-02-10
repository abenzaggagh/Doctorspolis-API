package com.doctorspolis.controller.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {


    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>("", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({ HandlerMethodValidationException.class })
    public ResponseEntity<Object> handleHandlerMethodValidationException(Exception exception) {
        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>("", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
