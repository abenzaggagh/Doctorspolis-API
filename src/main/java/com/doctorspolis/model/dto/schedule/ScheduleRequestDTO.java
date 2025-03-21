package com.doctorspolis.model.dto.schedule;

import com.doctorspolis.model.enumuration.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDTO {

    private Long doctorID;

    private Long patientID;

    private LocalDateTime dueDateTime;

    private AppointmentType appointmentType;

}
