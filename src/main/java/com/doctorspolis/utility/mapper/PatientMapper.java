package com.doctorspolis.utility.mapper;

import com.doctorspolis.model.data.Patient;
import com.doctorspolis.model.dto.PatientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);

}
