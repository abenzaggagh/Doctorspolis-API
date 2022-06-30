package com.doctorspolis.backend.model;

import com.doctorspolis.backend.utility.commun.AbstractEntity;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class WorkSchedule extends AbstractEntity {

    private String description;

    /*@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = )
    @JoinColumn(name = "WORK_SCHEDULE_ID",
            foreignKey = @ForeignKey(name = "FK_OPENING_HOURS_ID"),
            referencedColumnName = "ID") */
    @ElementCollection
    private Collection<OpeningHours> openingHours;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;


}
