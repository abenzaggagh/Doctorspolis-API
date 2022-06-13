package com.doctorspolis.backend.helper.mapper.referential;

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

    Country map(CountryDTO countryDTO);

    CountryDTO map(Country country);

    List<CountryDTO> map(List<Country> country);

}
