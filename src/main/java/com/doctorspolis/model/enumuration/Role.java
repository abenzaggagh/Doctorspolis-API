package com.doctorspolis.model.enumuration;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Role implements GrantedAuthority {

    ADMIN("Admin"),
    GUEST("Guest"),

    DOCTOR("Doctor"),
    PATIENT("Patient"),

    MODERATOR("Moderator");

    private final String label;


    @Override
    public String getAuthority() {
        return label;
    }

}
