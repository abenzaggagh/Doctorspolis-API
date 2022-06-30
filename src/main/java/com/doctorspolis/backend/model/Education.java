package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.referential.University;
import com.doctorspolis.backend.utility.commun.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Education extends AbstractEntity {

    private Date graduationDate;

    @OneToOne
    private University university;

}
