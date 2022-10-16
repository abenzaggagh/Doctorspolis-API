package com.doctorspolis.backend.model.referential;

import com.doctorspolis.backend.model.enumeration.Category;
import com.doctorspolis.backend.model.enumeration.Form;
import com.doctorspolis.backend.utility.commun.AbstractReferential;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name = "medication", uniqueConstraints = @UniqueConstraint(columnNames = { "code", "form" }) )
public class Medication extends AbstractReferential {

    private String description;

    private String indications;

    @Enumerated(EnumType.STRING)
    private Form form;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer composition;


}
