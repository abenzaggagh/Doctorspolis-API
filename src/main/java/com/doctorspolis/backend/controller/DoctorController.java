package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.commun.AbstractController;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api")
public class DoctorController extends AbstractController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public ResponseEntity<Collection<DoctorDTO>> getAllDoctors() {
        return ResponseEntity.ok(this.doctorService.getDoctors());
    }

    @GetMapping("/doctor/{ID}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long ID) throws DoctorNotFoundException {
        return ResponseEntity.ok(this.doctorService.getDoctorBy(ID));
    }

    @PostMapping("/doctor")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctor) {
        return ResponseEntity.ok(this.doctorService.createDoctor(doctor));
    }

    @PutMapping("/doctor/{doctorID}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long doctorID, @RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException {
        return ResponseEntity.ok(this.doctorService.updateDoctorByID(doctorID, doctorDTO));
    }

    @DeleteMapping("/doctor/{ID}")
    public void deleteDoctor(@PathVariable Long ID) {
        this.doctorService.deleteDoctorByID(ID);
    }

}
