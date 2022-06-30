package com.doctorspolis.backend.service;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.DTO.PatientDTO;
import com.doctorspolis.backend.model.Patient;
import com.doctorspolis.backend.model.repository.PatientRepository;
import com.doctorspolis.backend.utility.CRUDService;
import com.doctorspolis.backend.utility.commun.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PatientService extends AbstractService implements CRUDService<Patient, PatientDTO> {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    @Override
    public Collection<Patient> allEntities() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient oneEntity(Long patientID) throws ResourceNotFoundException {
        Optional<Patient> patient = patientRepository.findById(patientID);

        if (patient.isPresent())
            return patient.get();
        else
            throw new ResourceNotFoundException(patientID.toString());
    }

    @Override
    public Collection<PatientDTO> allDTOs() {
        return null;
    }

    @Override
    public PatientDTO oneDTO(Long ID) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public PatientDTO create(PatientDTO entityDTO) {
        return null;
    }

    @Override
    public PatientDTO updateByID(Long ID, PatientDTO entityDTO) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Boolean deleteByID(Long ID) throws ResourceNotFoundException {
        return null;
    }
}
