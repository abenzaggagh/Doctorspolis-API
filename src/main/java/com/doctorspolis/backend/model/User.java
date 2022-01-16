package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class User extends AbstractEntity {

    private @Column(length=200, nullable = false, unique = true) String username;
    private @Column(length=200, nullable = false) String password;

    private @Column(length=200, unique = true) String email;

}
