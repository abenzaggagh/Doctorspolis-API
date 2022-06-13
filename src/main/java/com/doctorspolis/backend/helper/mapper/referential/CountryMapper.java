package com.doctorspolis.backend.helper.mapper.referential;

import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;
import java.util.List;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    componentModel = "spring"
)
public interface CountryMapper {

    Country toEntity(CountryDTO countryDTO);

    CountryDTO toDTO(Country country);

    Collection<CountryDTO> toDTOs(Collection<Country> country);

}
