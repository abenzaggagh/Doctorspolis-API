package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.utility.commun.AbstractEntity;
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

    private @Column(nullable = false) String streetAddress;

    private String optionalStreetAddress;

    private @Column(nullable = false) String state;

    private @Column(nullable = false) String postalCode;

    private @Column(nullable = false) String city;

    private @OneToOne Country country;

}
