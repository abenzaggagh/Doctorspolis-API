package com.doctorspolis.backend.utility.mapper.referential;

import com.doctorspolis.backend.model.referential.DTO.SpecialityDTO;
import com.doctorspolis.backend.model.referential.Speciality;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface SpecialityMapper {

    SpecialityDTO toDTO(Speciality speciality);

    List<SpecialityDTO> toDTOs(List<Speciality> specialities);

}
