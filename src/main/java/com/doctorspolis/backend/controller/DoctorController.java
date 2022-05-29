package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.commun.CRUDController;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.DTO.PageDTO;
import com.doctorspolis.backend.service.DoctorService;
import com.doctorspolis.backend.utility.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(DoctorspolisConstants.DOCTORS)
public class DoctorController implements CRUDController<DoctorDTO> {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    @GetMapping()
    public ResponseEntity<Collection<DoctorDTO>> getAll() {
        return ResponseEntity.ok(this.doctorService.getDoctors());
    }

    @Override
    @GetMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<DoctorDTO> getOne(@PathVariable Long doctorID) throws DoctorNotFoundException {
        return ResponseEntity.ok(this.doctorService.getDoctorBy(doctorID));
    }

    @Override
    @PostMapping()
    public ResponseEntity<DoctorDTO> create(@Valid @RequestBody DoctorDTO doctor) {
        return ResponseEntity.ok(this.doctorService.createDoctor(doctor));
    }

    @Override
    @PutMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<DoctorDTO> update(@PathVariable Long doctorID,
                                            @RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException {
        return ResponseEntity.ok(this.doctorService.updateDoctorByID(doctorID, doctorDTO));
    }

    @Override
    @DeleteMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<Boolean> delete(@PathVariable Long doctorID) throws DoctorNotFoundException {
        return ResponseEntity.ok(this.doctorService.deleteDoctorByID(doctorID));
    }


    /* Keep the Search methods for later. */
    // TODO: Implement the search controller for doctors
    //       Change the GET method to POST
    @GetMapping(DoctorspolisConstants.SEARCH)
    public ResponseEntity<PageDTO<DoctorDTO>> search(String query, Pageable pageable) {
        return ResponseEntity.ok(this.doctorService.searchDoctors(query, pageable));
    }


}
