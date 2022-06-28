package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.DTO.PatientDTO;
import com.doctorspolis.backend.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {AddressMapper.class}
)
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);

    Collection<PatientDTO> toDTOs(Collection<Patient> patients);

    Patient toEntity(PatientDTO patientDTO);

    Collection<Patient> toEntities(Collection<PatientDTO> patientDTOS);

}
