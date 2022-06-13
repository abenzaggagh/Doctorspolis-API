package com.doctorspolis.backend.helper.mapper;

import com.doctorspolis.backend.model.DTO.WorkScheduleDTO;
import com.doctorspolis.backend.model.WorkSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface WorkScheduleMapper {

    WorkScheduleDTO map(WorkSchedule workSchedule);

    Collection<WorkScheduleDTO> map(Collection<WorkSchedule> workSchedules);

    WorkSchedule toEntity(WorkScheduleDTO workScheduleDTO);

    Collection<WorkSchedule> toEntities(Collection<WorkScheduleDTO> workScheduleDTOS);


}
