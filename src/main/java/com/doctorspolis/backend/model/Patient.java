package com.doctorspolis.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Patient extends Person {

    @OneToOne
    private User user;

}
