package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;
import com.doctorspolis.backend.model.enumeration.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)


@Entity
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {

    @Column(length=200, nullable = false, unique = true)
    private String username;

    @Column(length=200, nullable = false)
    private String password;

    @Column(length=200, unique = true)
    private String email;

    private Boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return Set.of(role);
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

}
