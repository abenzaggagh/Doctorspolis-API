package com.doctorspolis.model.data;

import com.doctorspolis.model.enumuration.AppointmentType;
import com.doctorspolis.model.enumuration.Availability;
import com.doctorspolis.model.enumuration.DurationEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.doctorspolis.utility.DoctorspolisConstants.DOCTOR_TABLE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = DOCTOR_TABLE)
public class Doctor extends Person {

    private Boolean isProfileVisible;

    @Column(length = 10)
    private String phone;

    @Column(length = 50)
    private String email;

    @Column(length = 2000)
    private String overview;

    @OneToOne
    private WorkPlace workPlace;

    @OneToOne
    private WorkSchedule workSchedule;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @Enumerated(EnumType.STRING)
    private DurationEnum appointmentDuration;

    @CollectionTable
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = AppointmentType.class)
    private List<AppointmentType> appointmentTypes;

    @OneToOne
    private User user;

}
