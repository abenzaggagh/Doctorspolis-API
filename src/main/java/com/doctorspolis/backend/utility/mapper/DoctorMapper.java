package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = { AddressMapper.class, WorkScheduleMapper.class }
)
public interface DoctorMapper {

    DoctorDTO toDTO(Doctor doctor);

    Collection<DoctorDTO> toDTOs(Collection<Doctor> doctors);

    Doctor toEntity(DoctorDTO doctor);

    Collection<DoctorDTO> toEntities(Collection<Doctor> doctors);

}
