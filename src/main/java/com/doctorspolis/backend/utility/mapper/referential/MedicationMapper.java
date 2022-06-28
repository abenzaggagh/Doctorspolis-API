package com.doctorspolis.backend.utility.mapper.referential;

import com.doctorspolis.backend.model.DTO.MedicationDTO;
import com.doctorspolis.backend.model.referential.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface MedicationMapper {

    MedicationDTO toDTO(Medication medication);

    List<MedicationDTO> toDTOs(List<Medication> medications);

}
