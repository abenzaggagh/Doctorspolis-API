package com.doctorspolis.model.data.doctor;

import com.doctorspolis.model.enumuration.Day;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)

@Embeddable
public class OpeningHours {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day day;

    private Boolean opens;

    private Boolean hasBreak;

    @Temporal(TemporalType.TIME)
    private LocalTime openingTime;

    @Temporal(TemporalType.TIME)
    private LocalTime closingTime;

    @Temporal(TemporalType.TIME)
    private LocalTime breakTime;

    @Temporal(TemporalType.TIME)
    private LocalTime resumeTime;

}
