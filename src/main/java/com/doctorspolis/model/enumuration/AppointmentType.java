package com.doctorspolis.model.enumuration;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter

public enum AppointmentType {
    IN_PERSON("IN_PERSON"),
    VIDEO_CALL("VIDEO_CALL"),
    AT_HOME("AT_HOME");

    @JsonValue
    private final String label;

    AppointmentType(String label) {
        this.label = label;
    }
}
