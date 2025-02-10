package com.doctorspolis.utility.mapper;

import com.doctorspolis.model.data.doctor.Doctor;
import com.doctorspolis.model.dto.doctor.DoctorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {
                SpecialityMapper.class,
                WorkPlaceMapper.class,
                WorkScheduleMapper.class
        }
)
public interface DoctorMapper {

    DoctorDTO toDTO(Doctor doctor);

    List<DoctorDTO> toDTOs(List<Doctor> doctor);

}
