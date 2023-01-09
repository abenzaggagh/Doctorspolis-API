package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.model.DTO.AppointmentDTO;
import com.doctorspolis.backend.service.AppointmentService;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(DoctorspolisConstants.APPOINTMENTS)
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("available")
    public ResponseEntity<AppointmentDTO> available(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.make(appointmentDTO));
    }

    @PostMapping("request")
    public ResponseEntity<AppointmentDTO> request(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.make(appointmentDTO));
    }

    @PostMapping("make")
    public ResponseEntity<AppointmentDTO> make(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.make(appointmentDTO));
    }

    @PostMapping("cancel/{appointmentID}")
    public ResponseEntity<Boolean> cancel(@PathVariable Long appointmentID) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(appointmentService.cancel(appointmentID));
    }

}
