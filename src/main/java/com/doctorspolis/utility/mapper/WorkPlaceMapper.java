package com.doctorspolis.utility.mapper;

import com.doctorspolis.model.data.doctor.WorkPlace;
import com.doctorspolis.model.dto.doctor.WorkPlaceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = { AddressMapper.class }
)
public interface WorkPlaceMapper {

    WorkPlaceDTO toDTO(WorkPlace workPlace);

    WorkPlace toEntity(WorkPlaceDTO workPlaceDTO);

}
