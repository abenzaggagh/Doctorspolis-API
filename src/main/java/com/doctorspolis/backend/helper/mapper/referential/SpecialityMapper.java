package com.doctorspolis.backend.helper.mapper.referential;

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

    SpecialityDTO map(Speciality speciality);

    List<SpecialityDTO> map(List<Speciality> specialities);

}
