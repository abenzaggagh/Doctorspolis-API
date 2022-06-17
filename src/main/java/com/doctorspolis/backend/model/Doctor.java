package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.enumeration.Availability;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@DynamicUpdate
public class Doctor extends Person {

    private @Column(length=500) String overview;

    @OneToOne()
    private Address address;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Language> languages;

    @OneToMany
    private Collection<Education> educations;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Collection<Speciality> Specialities;

    private @Enumerated(EnumType.STRING) Availability availability;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID")
    private Collection<WorkSchedule> workSchedule;

}
