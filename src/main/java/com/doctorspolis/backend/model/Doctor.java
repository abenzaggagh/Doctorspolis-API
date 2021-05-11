package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.enumeration.Availability;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Doctor extends Person {

    private @Column(length=500) String overview;

    private @Enumerated(EnumType.STRING) Availability availability;

    @OneToMany()
    private Collection<Speciality> Specialities;

    @OneToMany()
    private Collection<Language> languages;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Collection<OpeningHours> openingHours;

}
