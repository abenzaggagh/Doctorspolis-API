package com.doctorspolis.backend.helper.mapper;

import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    componentModel = "spring"
)
public interface CountryMapper {

    CountryDTO map(Country country);

    List<CountryDTO> map(List<Country> country);

}
