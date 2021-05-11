package com.doctorspolis.backend.model;

import lombok.*;

import javax.persistence.Entity;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)

@Entity
public class Patient extends Person {

}
