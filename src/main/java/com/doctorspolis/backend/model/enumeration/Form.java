package com.doctorspolis.backend.model.enumeration;

import lombok.Getter;

@Getter
public enum Form {

    TABLET("TABLET"),
    CAPSULE("CAPSULE"),
    LIQUID("LIQUID"),
    CREAM("CREAM"),
    PATCH("PATCH"),
    PILL("PILL");

    private final String label;

    Form(String label) {
        this.label = label;
    }
    
}