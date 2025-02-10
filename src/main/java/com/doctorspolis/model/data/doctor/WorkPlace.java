package com.doctorspolis.model.data.doctor;

import com.doctorspolis.model.data.Address;
import com.doctorspolis.utility.AbstractEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class WorkPlace extends AbstractEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Column(nullable = false)
    private String institutionName;

    private Float latitude;

    private Float longitude;

}
