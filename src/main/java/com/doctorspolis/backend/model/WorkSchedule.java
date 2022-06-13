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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "WORK_SCHEDULE_ID")
    private Collection<OpeningHours> openingHours;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Doctor doctor;

}
