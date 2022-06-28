package com.doctorspolis.backend.model.referential;

import com.doctorspolis.backend.commun.AbstractReferential;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Medication extends AbstractReferential {

    private Category category;

    public enum Category {
        UNRESTRICTED, PHARMACY, PRESCRIPTION, CONTROLLED
    }


}
