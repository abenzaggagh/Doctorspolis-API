package com.doctorspolis.model.data.doctor;

import com.doctorspolis.model.data.*;
import com.doctorspolis.model.enumuration.AppointmentType;
import com.doctorspolis.model.enumuration.Availability;
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

    @ManyToOne
    private Speciality speciality;

    @ManyToMany
    private List<Language> languages;

    @OneToMany
    private List<Education> educations;

    @CollectionTable
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = AppointmentType.class)
    private List<AppointmentType> appointmentTypes;

    @OneToMany
    private List<AppointmentReason> appointmentReasons;

    @OneToOne
    private User user;

}
