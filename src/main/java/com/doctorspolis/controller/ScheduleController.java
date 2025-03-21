package com.doctorspolis.controller;

import com.doctorspolis.model.dto.schedule.ScheduleRequestDTO;
import com.doctorspolis.model.dto.schedule.ScheduleDTO;
import com.doctorspolis.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.doctorspolis.utility.DoctorspolisConstants.SCHEDULE_ENDPOINT;

@RequiredArgsConstructor

@RestController
@RequestMapping(SCHEDULE_ENDPOINT)
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("available/{doctorID}")
    public List<ScheduleDTO> availableAppointments(@PathVariable Long doctorID) {
        return scheduleService.availableAppointments(doctorID);
    }

    @PostMapping("book")
    public Boolean bookAppointment(@RequestBody ScheduleRequestDTO scheduleAppointmentDTO) {
        return scheduleService.bookAppointment(scheduleAppointmentDTO);
    }



}
