package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.enumeration.Role;
import com.doctorspolis.backend.utility.commun.AbstractEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter

@Builder

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)


@Entity
@Table(name = "users")
public class User /* extends Person */ extends AbstractEntity implements UserDetails {

    @Column(length = 200, nullable = false, unique = true)
    private String username;

    @Column(length = 200, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean enabled;

    // @Column(nullable = false)
    private String refreshToken;


    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return Objects.isNull(role) ? new HashSet<>() : Set.of(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public boolean isAdmin() {
        return role.equals(Role.ADMIN);
    }

    public boolean isDoctor() {
        return role == Role.DOCTOR;
    }

    public boolean isPatient() {
        return role.equals(Role.PATIENT);
    }

    public boolean roleEquals(Role role) {
        return this.role.equals(role);
    }

}
