package com.doctorspolis.backend.model.referential;

import com.doctorspolis.backend.commun.AbstractReferential;
import com.doctorspolis.backend.model.enumeration.Category;
import com.doctorspolis.backend.model.enumeration.Form;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Medication extends AbstractReferential {

    @Enumerated(EnumType.STRING)
    private Form form;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer composition;


}
