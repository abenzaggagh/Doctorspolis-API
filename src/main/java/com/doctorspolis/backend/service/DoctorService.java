package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.helper.DoctorHelper;
import com.doctorspolis.backend.helper.mapper.DoctorMapper;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.repository.DoctorRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class DoctorService extends AbstractService {

    private final DoctorMapper doctorMapper;

    private final DoctorHelper doctorHelper;

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorMapper doctorMapper,
                         DoctorHelper doctorHelper,
                         DoctorRepository doctorRepository) {
        this.doctorMapper = doctorMapper;
        this.doctorHelper = doctorHelper;
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> getDoctors() {
        return this.doctorRepository.findAll().stream().map(doctorMapper::map).collect(Collectors.toList());
    }

    public DoctorDTO getDoctorBy(Long doctorID) throws DoctorNotFoundException {
        Optional<Doctor> doctor = this.doctorRepository.findById(doctorID);
        if (doctor.isPresent())
            return doctorMapper.map(doctor.get());
        else
            throw new DoctorNotFoundException(doctorID);
    }

    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.map(doctorDTO);

        doctorHelper.setLanguages(doctorDTO, doctor);
        doctorHelper.setSpecialities(doctorDTO, doctor);

        return doctorMapper.map(this.doctorRepository.save(doctor));
    }

    public DoctorDTO updateDoctorByID(Long doctorID, DoctorDTO doctorDTO) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            Doctor doctor = doctorMapper.map(doctorDTO);

            doctorHelper.updateDoctor(doctor, existingDoctor);

            return doctorMapper.map(this.doctorRepository.save(doctor));
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    public void deleteDoctorByID(Long doctorID) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);
        optionalDoctor.ifPresentOrElse(
                doctor -> this.doctorRepository.deleteById(doctor.getID()),
                () -> { throw new DoctorNotFoundException(doctorID); }
        );
    }

}
