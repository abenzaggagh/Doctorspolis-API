package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.enumeration.Availability;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Doctor extends Person {

    private @Column(length=500) String overview;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Language> languages;

    @OneToMany
    private Collection<Education> educations;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Speciality> Specialities;

    private @Enumerated(EnumType.STRING) Availability availability;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="DOCTOR_ID", referencedColumnName="ID")
    private Collection<WorkSchedule> workSchedule;

}
