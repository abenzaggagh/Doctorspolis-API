package com.doctorspolis.backend.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString

@Builder

@NoArgsConstructor
@AllArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorDTO {
    // Error Code - For business purpose
    private String error;
    // TODO: Change the status to an enum
    // private String status;
    private HttpStatus status;

    private String message;
    // TODO: Change the type of details to a list
    private String details;

}
