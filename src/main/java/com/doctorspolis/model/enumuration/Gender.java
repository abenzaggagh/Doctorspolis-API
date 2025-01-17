package com.doctorspolis.model.enumuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape= JsonFormat.Shape.STRING)
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    @JsonValue
    private final String label;

}
