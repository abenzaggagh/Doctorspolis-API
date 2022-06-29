package com.doctorspolis.backend.service;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.DTO.PrescriptionDTO;
import com.doctorspolis.backend.model.Patient;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.model.repository.PatientRepository;
import com.doctorspolis.backend.model.repository.PrescriptionRepository;
import com.doctorspolis.backend.utility.mapper.PrescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

@Service
public class PrescriptionService {


    private final PatientRepository patientRepository;

    private final PrescriptionMapper prescriptionMapper;

    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(PatientRepository patientRepository,
                               PrescriptionMapper prescriptionMapper,
                               PrescriptionRepository prescriptionRepository) {
        this.patientRepository = patientRepository;
        this.prescriptionMapper = prescriptionMapper;
        this.prescriptionRepository = prescriptionRepository;
    }

    public ResponseEntity<Collection<PrescriptionDTO>> all() {
        Patient patient = patientRepository.getById(1L);
        return ResponseEntity.ok(prescriptionMapper.toDTOs(prescriptionRepository.findAllByPatient(patient)));
    }

    public Collection<PrescriptionDTO> allByPatientID(User user, Long patientID) throws ResourceNotFoundException {
        if ((!ObjectUtils.isEmpty(user) && !user.getID().equals(patientID) || !user.isAdmin())) {
            // TODO: Change th e Exception for an Forbidden resource
            throw new ResourceNotFoundException("dddd");
        }
        Patient patient = patientRepository.getById(patientID);
        return prescriptionMapper.toDTOs(prescriptionRepository.findAllByPatient(patient));
    }


}
