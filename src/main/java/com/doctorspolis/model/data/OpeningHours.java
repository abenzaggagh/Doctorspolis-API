package com.doctorspolis.model.data;

import com.doctorspolis.model.enumuration.Day;
import jakarta.persistence.*;
import lombok.*;

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
    private Date openingTime;

    @Temporal(TemporalType.TIME)
    private Date closingTime;

    @Temporal(TemporalType.TIME)
    private Date breakTime;

    @Temporal(TemporalType.TIME)
    private Date resumeTime;

}
