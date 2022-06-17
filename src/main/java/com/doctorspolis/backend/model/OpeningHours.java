package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.enumeration.Day;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Embeddable
public class OpeningHours /* extends AbstractEntity */ {

    private @Enumerated(EnumType.STRING)
    @Column(nullable = false) Day day;

    private Boolean opens;

    private @Temporal(TemporalType.TIME) Date openingTime;

    private @Temporal(TemporalType.TIME) Date closingTime;

}
