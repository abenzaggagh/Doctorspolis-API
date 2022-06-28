package com.doctorspolis.backend.utility.mapper.referential;

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
