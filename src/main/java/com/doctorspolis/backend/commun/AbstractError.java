package com.doctorspolis.backend.commun;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public abstract class AbstractError {

    private HttpStatus status;

    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "dd-MM-yyyy hh:mm:ss"
    )
    private Timestamp timestamp;

    private String message;

}
