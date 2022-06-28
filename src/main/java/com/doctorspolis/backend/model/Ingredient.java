package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.enumeration.Frequency;
import com.doctorspolis.backend.model.referential.Medication;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Embeddable
public class Ingredient {

    @OneToOne
    private Medication medication;

    private Integer quantity; // In MG

    private Integer treatmentLength; // In Days

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

}