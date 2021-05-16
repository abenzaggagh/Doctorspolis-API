package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.repository.DoctorRepository;
import com.doctorspolis.backend.repository.referential.LanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService extends AbstractService {

    ModelMapper modelMapper = new ModelMapper();

    private final LanguageRepository languageRepository;

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository,
                         LanguageRepository languageRepository) {
        this.doctorRepository = doctorRepository;
        this.languageRepository = languageRepository;
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

    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);

        ArrayList<Language> languages = new ArrayList<>();

        if (doctorDTO.getLanguages() != null && doctorDTO.getLanguages().size() > 0) {
            for(LanguageDTO languageDTO: doctorDTO.getLanguages()) {
                languages.add(this.languageRepository.findLanguageByCode(languageDTO.getCode()));
            }
            doctor.setLanguages(languages);
        }

        return modelMapper.map(this.doctorRepository.save(doctor), DoctorDTO.class);
    }

}
