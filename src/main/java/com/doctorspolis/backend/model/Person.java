package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;

import com.doctorspolis.backend.model.enumeration.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@MappedSuperclass
// A class should not be annotated with both @Inheritance and @MappedSuperclass.
// @Inheritance will be ignored for: com.doctorspolis.backend.model.Person.
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person extends AbstractEntity {

    private @Column(length=50) String firstname;
    private @Column(length=50) String surname;
    private @Column(length=50) String lastname;

    private @Enumerated(EnumType.STRING) Gender gender;

    private @Temporal(TemporalType.DATE) Date birthday;

    private @Column(length=20, unique = true) String phone;
    private @Column(length=100, unique = true) String email;

    @OneToMany()
    private Collection<Address> addresses;

}
