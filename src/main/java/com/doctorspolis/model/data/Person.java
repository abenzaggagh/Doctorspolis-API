package com.doctorspolis.model.data;

import com.doctorspolis.model.enumuration.Gender;
import com.doctorspolis.utility.AbstractEntity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@MappedSuperclass
public abstract class Person extends AbstractEntity {

    @Column(length = 100)
    private String profilePicture;

    @Column(length = 50)
    private String firstname;

    @Column(length = 50)
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birthday;

}
