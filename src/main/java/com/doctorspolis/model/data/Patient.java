package com.doctorspolis.model.data;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.doctorspolis.utility.DoctorspolisConstants.PATIENT_TABLE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = PATIENT_TABLE)
public class Patient extends Person {

    @OneToOne
    private User user;

}
