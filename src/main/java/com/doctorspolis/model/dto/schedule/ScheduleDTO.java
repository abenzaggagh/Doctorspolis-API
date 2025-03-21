package com.doctorspolis.model.dto.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Boolean isBooked;

}
