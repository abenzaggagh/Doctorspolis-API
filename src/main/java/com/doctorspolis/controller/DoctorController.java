package com.doctorspolis.controller;

import com.doctorspolis.model.data.User;
import com.doctorspolis.model.data.doctor.WorkSchedule;
import com.doctorspolis.model.dto.doctor.DoctorDTO;
import com.doctorspolis.model.dto.doctor.WorkPlaceDTO;
import com.doctorspolis.model.dto.doctor.WorkScheduleDTO;
import com.doctorspolis.service.DoctorService;
import com.doctorspolis.utility.validator.WorkScheduleValid;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.doctorspolis.utility.DoctorspolisConstants.DOCTOR_ENDPOINT;

@RequiredArgsConstructor

@RestController
@RequestMapping(DOCTOR_ENDPOINT)
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("work-schedule")
    public void workSchedule(@AuthenticationPrincipal User user,
                             @RequestBody @WorkScheduleValid WorkScheduleDTO workScheduleDTO) {
       this.doctorService.workSchedule(user, workScheduleDTO);
   }

    @PostMapping("work-place")
    public void workSchedule(@AuthenticationPrincipal User user,
                             @RequestBody WorkPlaceDTO workPlaceDTO) {
        this.doctorService.workPlace(user, workPlaceDTO);
    }

}
