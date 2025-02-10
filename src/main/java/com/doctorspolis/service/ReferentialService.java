package com.doctorspolis.service;

import com.doctorspolis.model.dto.doctor.SpecialityDTO;
import com.doctorspolis.repository.SpecialityRepository;
import com.doctorspolis.utility.mapper.SpecialityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ReferentialService {

    private final SpecialityMapper specialityMapper;
    private final SpecialityRepository specialityRepository;

    public List<SpecialityDTO> specialities() {
        return specialityMapper.toDTOs(this.specialityRepository.findAll());
    }

    public
    SpecialityDTO speciality(String code) {
        return specialityMapper.toDTO(this.specialityRepository.findByCode(code));
    }

}
