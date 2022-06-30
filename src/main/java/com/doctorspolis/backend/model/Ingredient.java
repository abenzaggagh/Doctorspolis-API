package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.enumeration.Frequency;
import com.doctorspolis.backend.model.referential.Medication;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Embeddable
public class Ingredient {

    @OneToOne(fetch = FetchType.LAZY)
    private Medication medication;

    private Integer quantity; // Times

    private Integer treatmentLength; // In Days

    private Date treatmentStartingDate;

    private Date treatmentEndingDate;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

}