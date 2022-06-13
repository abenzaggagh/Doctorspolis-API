package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class WorkSchedule extends AbstractEntity {

    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "WORK_SCHEDULE_ID",
            foreignKey = @ForeignKey(name = "FK_OPENING_HOURS_ID"),
            referencedColumnName = "ID")
    private Collection<OpeningHours> openingHours;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;

}
