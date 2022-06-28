package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.Address;
import com.doctorspolis.backend.model.DTO.AddressDTO;
import com.doctorspolis.backend.utility.mapper.referential.CountryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = { CountryMapper.class }
)
public interface AddressMapper {

    AddressDTO toDTO(Address address);

    Address toEntity(AddressDTO address);

}
