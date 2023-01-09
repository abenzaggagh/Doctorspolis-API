package com.doctorspolis.backend.controller.exception.handler;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.controller.exception.TokenExpiredException;
import com.doctorspolis.backend.model.DTO.ErrorDTO;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class, TokenExpiredException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception exception, WebRequest request) {
        return new ResponseEntity<Object>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<ErrorDTO> handleResourceNotFoundException(Exception exception, WebRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAcceptCharset(List.of(StandardCharsets.UTF_8, StandardCharsets.UTF_16));

        ErrorDTO errorDTO = ErrorDTO.builder()
                                    .error(request.getParameter("code"))
                                    .status(HttpStatus.NOT_FOUND)
                                    .message(exception.getMessage())
                                    .details(String.valueOf(exception))
                                    .build();

        return new ResponseEntity<>(errorDTO, httpHeaders, HttpStatus.NOT_FOUND);
    }

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request
    ) {    Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) ->{

        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(RuntimeException exception, WebRequest request){
        return new ResponseEntity<>(ErrorDTO
                .builder()
                .error("10001")
                .status(INTERNAL_SERVER_ERROR)
                .message("")
                .details("")
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(@NonNull Exception ex, @Nullable Object body, @NonNull HttpHeaders headers, @NonNull HttpStatus status, @NonNull WebRequest request) {
        return new ResponseEntity<>(ErrorDTO
                .builder()
                .error("10001")
                .status(INTERNAL_SERVER_ERROR)
                .message("")
                .details("")
                .build(),
                HttpStatus.BAD_REQUEST);
    }

}
