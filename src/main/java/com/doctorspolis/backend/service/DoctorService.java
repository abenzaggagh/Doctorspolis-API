package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService extends AbstractService {

    ModelMapper modelMapper = new ModelMapper();

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
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
            throw new DoctorNotFoundException();
    }

    public DoctorDTO createDoctor(Doctor doctor) {
        return modelMapper.map(this.doctorRepository.save(doctor), DoctorDTO.class);
    }

}
