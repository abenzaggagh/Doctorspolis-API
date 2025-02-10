package com.doctorspolis.utility.mapper;

import com.doctorspolis.model.data.doctor.Speciality;
import com.doctorspolis.model.dto.doctor.SpecialityDTO;
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
