package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.DTO.PrescriptionDTO;
import com.doctorspolis.backend.model.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = { IngredientMapper.class }
)
public interface PrescriptionMapper {

    PrescriptionDTO toDTO(Prescription prescription);

    Collection<PrescriptionDTO> toDTOs(Collection<Prescription> prescriptions);

    Prescription toEntity(PrescriptionDTO prescriptionDTO);

    Collection<Prescription> toEntities(Collection<PrescriptionDTO> prescriptionDTOS);


}
