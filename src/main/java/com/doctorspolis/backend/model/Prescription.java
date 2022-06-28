package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter

@Builder

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Prescription extends AbstractEntity {

    @OneToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    private Date issueDate;

    @ElementCollection
    private Collection<Ingredient> ingredients;




    // Instructions

}
