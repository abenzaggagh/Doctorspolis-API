package com.doctorspolis.backend.exception.handler;

import com.doctorspolis.backend.model.DTO.ErrorDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationExceptionHandler {

    @ExceptionHandler({ JwtException.class,
                        ExpiredJwtException.class })
    protected ResponseEntity<ErrorDTO> handleTokenExpired(Exception exception) {
        return new ResponseEntity<>(ErrorDTO
                .builder()
                .error("00001")
                .status("400")
                .message(exception.getMessage())
                .details(exception.getMessage())
                .build(),
                HttpStatus.UNAUTHORIZED);
    }

}