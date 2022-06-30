package com.doctorspolis.backend.model.referential;

import com.doctorspolis.backend.utility.commun.AbstractReferential;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Speciality extends AbstractReferential {

    private String description;

}