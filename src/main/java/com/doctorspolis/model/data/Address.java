package com.doctorspolis.model.data;

import com.doctorspolis.utility.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Address extends AbstractEntity {

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;

}
