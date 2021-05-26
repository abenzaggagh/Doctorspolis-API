package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.helper.DoctorHelper;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.repository.DoctorRepository;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class DoctorService extends AbstractService {

    ModelMapper modelMapper = new ModelMapper();

    private final DoctorHelper doctorHelper;

    private final DoctorRepository doctorRepository;

    // TODO : Finish the Update methods for PUT/PATCH Http Methods

    @Autowired
    public DoctorService(DoctorHelper doctorHelper,
                         DoctorRepository doctorRepository) {
        this.doctorHelper = doctorHelper;
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> getDoctors() {
        return this.doctorRepository.findAll().stream().map(doctor -> modelMapper.map(doctor, DoctorDTO.class)).collect(Collectors.toList());
    }

    public DoctorDTO getDoctorBy(Long ID) throws DoctorNotFoundException {
        Optional<Doctor> doctor = this.doctorRepository.findById(ID);
        if (doctor.isPresent())
            return modelMapper.map(doctor.get(), DoctorDTO.class);
        else
            throw new DoctorNotFoundException("");
    }

    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);

        doctorHelper.setLanguages(doctorDTO, doctor);
        doctorHelper.setSpecialities(doctorDTO, doctor);

        return modelMapper.map(this.doctorRepository.save(doctor), DoctorDTO.class);
    }

    public DoctorDTO updateDoctorByID(Long doctorID, DoctorDTO doctorDTO) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);

            doctorHelper.updateDoctor(doctor, existingDoctor);

            return modelMapper.map(this.doctorRepository.save(doctor), DoctorDTO.class);
        } else {
            throw new DoctorNotFoundException("");
        }
    }


}
