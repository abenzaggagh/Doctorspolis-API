package com.doctorspolis.controller;

import com.doctorspolis.model.data.authentication.User;
import com.doctorspolis.model.dto.doctor.DoctorDTO;
import com.doctorspolis.model.dto.doctor.WorkPlaceDTO;
import com.doctorspolis.model.dto.doctor.WorkScheduleDTO;
import com.doctorspolis.service.DoctorService;
import com.doctorspolis.utility.validator.WorkScheduleValid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.doctorspolis.utility.DoctorspolisConstants.DOCTOR_ENDPOINT;

@RequiredArgsConstructor

@RestController
@RequestMapping(DOCTOR_ENDPOINT)
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("{doctorID}")
    public ResponseEntity<DoctorDTO> searchDoctor(@PathVariable Long doctorID) {
        return ResponseEntity.ok(this.doctorService.getDoctorByID(doctorID));
    }

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
