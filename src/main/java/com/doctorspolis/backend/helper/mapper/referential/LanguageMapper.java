package com.doctorspolis.backend.helper.mapper.referential;

import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;
import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.model.referential.Language;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    componentModel = "spring"
)
public interface LanguageMapper {

    LanguageDTO toDTO(Language language);

    List<LanguageDTO> toDTOs(List<Language> languages);

}
