package com.doctorspolis.utility.mapper;

import com.doctorspolis.model.data.Address;
import com.doctorspolis.model.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface AddressMapper {

    AddressDTO toDTO(Address address);

}
