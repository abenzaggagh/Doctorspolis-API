package com.doctorspolis.model.data.doctor;


import com.doctorspolis.utility.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Education extends AbstractEntity {

    private String university;

    private String diploma;

    private LocalDate graduation;

}
