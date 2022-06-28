package com.doctorspolis.backend.model;

import com.doctorspolis.backend.commun.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter

@Builder

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Entity
public class Appointment extends AbstractEntity {

    @OneToOne
    private Doctor doctor;

    @OneToOne
    private Patient patient;

    private String description;

    private Integer periodTime;

    private LocalDateTime dueDateTime;

    private Boolean canceled;

}
