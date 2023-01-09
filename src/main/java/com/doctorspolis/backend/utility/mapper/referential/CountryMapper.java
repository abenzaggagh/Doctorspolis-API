package com.doctorspolis.backend.utility.mapper.referential;

import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CountryMapper {

    @Mapping(target = DoctorspolisConstants.ID, ignore = true)
    Country toEntity(CountryDTO countryDTO);

    CountryDTO toDTO(Country country);

    Collection<CountryDTO> toDTOs(Collection<Country> country);

}
