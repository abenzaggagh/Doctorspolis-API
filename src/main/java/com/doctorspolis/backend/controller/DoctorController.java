package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.commun.AbstractController;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.service.DoctorService;
import com.doctorspolis.backend.utility.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(DoctorspolisConstants.DOCTORS)
public class DoctorController extends AbstractController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public ResponseEntity<Collection<DoctorDTO>> getAllDoctors() {
        return ResponseEntity.ok(this.doctorService.getDoctors());
    }

    // TODO: Implement the search controller for doctors
    @GetMapping(DoctorspolisConstants.SEARCH)
    public ResponseEntity<Page<DoctorDTO>> searchDoctors(String query,
                                                         Pageable pageable) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable Long doctorID) throws DoctorNotFoundException {
        return ResponseEntity.ok(this.doctorService.getDoctorBy(doctorID));
    }

    @PostMapping()
    public ResponseEntity<DoctorDTO> createDoctor(@Valid @RequestBody DoctorDTO doctor) {
        return ResponseEntity.ok(this.doctorService.createDoctor(doctor));
    }

    @PutMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long doctorID,
                                                  @RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException {
        return ResponseEntity.ok(this.doctorService.updateDoctorByID(doctorID, doctorDTO));
    }

    @DeleteMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public void deleteDoctor(@PathVariable Long doctorID) {
        this.doctorService.deleteDoctorByID(doctorID);
    }

}
