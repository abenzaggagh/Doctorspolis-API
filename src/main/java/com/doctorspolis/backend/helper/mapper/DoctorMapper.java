package com.doctorspolis.backend.helper.mapper;

import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface DoctorMapper {

    DoctorDTO map(Doctor doctor);

    Doctor map(DoctorDTO doctor);

}
