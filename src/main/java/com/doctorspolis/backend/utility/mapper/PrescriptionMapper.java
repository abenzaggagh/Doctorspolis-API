package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.DTO.PrescriptionDTO;
import com.doctorspolis.backend.model.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {DoctorMapper.class, PatientMapper.class, IngredientMapper.class}
)
public interface PrescriptionMapper {

    @Mappings({
            @Mapping(target = "doctor.gender", ignore = true),
            @Mapping(target = "doctor.overview", ignore = true),
            @Mapping(target = "doctor.languages", ignore = true),
            @Mapping(target = "doctor.availability", ignore = true),
            @Mapping(target = "doctor.specialities", ignore = true),
            @Mapping(target = "doctor.workSchedule", ignore = true)
    })
    PrescriptionDTO toDTO(Prescription prescription);

    Collection<PrescriptionDTO> toDTOs(Collection<Prescription> prescriptions);

    Prescription toEntity(PrescriptionDTO prescriptionDTO);

    Collection<Prescription> toEntities(Collection<PrescriptionDTO> prescriptionDTOS);


}
