package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.model.DTO.PrescriptionDTO;
import com.doctorspolis.backend.model.Patient;
import com.doctorspolis.backend.model.repository.PatientRepository;
import com.doctorspolis.backend.model.repository.PrescriptionRepository;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import com.doctorspolis.backend.utility.mapper.PrescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(DoctorspolisConstants.PRESCRIPTIONS)
public class PrescriptionController {

    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @GetMapping
    public ResponseEntity<Collection<PrescriptionDTO>> all() {
        Patient patient = patientRepository.getById(1L);
        return ResponseEntity.ok(prescriptionMapper.toDTOs(prescriptionRepository.findAllByPatient(patient)));
    }

}
