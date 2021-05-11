package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;
import com.doctorspolis.backend.model.referential.Country;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Address extends AbstractEntity {

    private String postalCode;

    private String city;

    @OneToOne()
    private Country country;

}
