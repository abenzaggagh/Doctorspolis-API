package com.doctorspolis.model.data.authentication;

import com.doctorspolis.model.enumuration.Role;
import com.doctorspolis.utility.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static com.doctorspolis.utility.DoctorspolisConstants.USERS_TABLE;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = USERS_TABLE)
public class User extends AbstractEntity implements UserDetails {

    @Column(length = 300, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String phone;

    @Column(length = 300, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Objects.isNull(role) ? new HashSet<>() : Set.of(role);
    }

    @Override
    public String getUsername() {
        return email;
    }


}
