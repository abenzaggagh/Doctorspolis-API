package com.doctorspolis.backend.helper.mapper;

import com.doctorspolis.backend.model.Address;
import com.doctorspolis.backend.model.DTO.AddressDTO;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface AddressMapper {

    AddressDTO map(Address address);

    Address map(AddressDTO address);

}
