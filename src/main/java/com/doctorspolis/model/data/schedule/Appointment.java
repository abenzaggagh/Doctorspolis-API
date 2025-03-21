package com.doctorspolis.model.data.schedule;


import com.doctorspolis.model.data.Patient;
import com.doctorspolis.model.data.doctor.Doctor;
import com.doctorspolis.model.enumuration.AppointmentType;
import com.doctorspolis.utility.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.doctorspolis.utility.DoctorspolisConstants.APPOINTMENT_TABLE;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = APPOINTMENT_TABLE)
public class Appointment extends AbstractEntity {

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String description;

    private Boolean patientShowUp;

    private Boolean canceled;

}
