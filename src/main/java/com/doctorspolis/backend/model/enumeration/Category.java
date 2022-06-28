package com.doctorspolis.backend.model.enumeration;

import lombok.Getter;

@Getter
public enum Category {

    UNRESTRICTED("UNRESTRICTED"),
    PHARMACY("PHARMACY"),
    PRESCRIPTION("PRESCRIPTION"),
    CONTROLLED("CONTROLLED");

    private final String label;

    Category(String label) {
        this.label = label;
    }
}