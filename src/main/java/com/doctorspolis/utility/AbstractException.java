package com.doctorspolis.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractException extends RuntimeException {

    private String message;

    private String details;

    private String errorCode;

    private HttpStatus httpStatus;

}
