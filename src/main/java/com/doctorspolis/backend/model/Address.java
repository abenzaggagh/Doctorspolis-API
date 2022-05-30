package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;
import com.doctorspolis.backend.model.referential.Country;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Address extends AbstractEntity {

    private @Column(nullable = false) String streetAddress;

    private String optionalStreetAddress;

    private @Column(nullable = false) String state;

    private @Column(nullable = false) String postalCode;

    private @Column(nullable = false) String city;

    @OneToOne()
    private Country country;

}
