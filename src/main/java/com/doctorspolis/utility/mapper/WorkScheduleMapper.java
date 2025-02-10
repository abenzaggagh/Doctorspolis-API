package com.doctorspolis.utility.mapper;

import com.doctorspolis.model.data.doctor.WorkSchedule;
import com.doctorspolis.model.dto.doctor.WorkScheduleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface WorkScheduleMapper {

    WorkScheduleDTO toDTO(WorkSchedule workSchedule);

    WorkSchedule toEntity(WorkScheduleDTO workScheduleDTO);

}
