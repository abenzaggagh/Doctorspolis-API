package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.DTO.PatientDTO;
import com.doctorspolis.backend.utility.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PatientService extends AbstractService implements CRUDService<PatientDTO> {

    @Autowired
    public PatientService() {}


    @Override
    public Collection<PatientDTO> getAll() {
        return null;
    }

    @Override
    public PatientDTO getOneBy(Long ID) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public PatientDTO createDoctor(PatientDTO entityDTO) {
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
