package com.doctorspolis.model.data.doctor;

import com.doctorspolis.utility.AbstractEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
public class WorkSchedule extends AbstractEntity {

    private Boolean active;

    private String description;

    @ElementCollection
    private Collection<OpeningHours> openingHours;

}
