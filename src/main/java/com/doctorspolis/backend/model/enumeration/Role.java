package com.doctorspolis.backend.model.enumeration;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {

    ADMIN("Admin"),
    USER("User"),
    MODERATOR("Moderator"),
    GUEST("GUEST");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    @Override
    public String getAuthority() {
        return label;
    }

}
