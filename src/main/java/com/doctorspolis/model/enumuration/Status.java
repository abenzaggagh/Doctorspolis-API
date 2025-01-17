package com.doctorspolis.model.enumuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape= JsonFormat.Shape.STRING)
public enum Status {
    SUCCESS("success"),
    FAILED("failed");

    @JsonValue
    private final String value;

}
