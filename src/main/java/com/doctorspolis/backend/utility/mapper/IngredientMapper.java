package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.DTO.IngredientDTO;
import com.doctorspolis.backend.model.Ingredient;
import com.doctorspolis.backend.utility.mapper.referential.MedicationMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = {MedicationMapper.class}
)
public interface IngredientMapper {

    @Mappings({
            @Mapping(target = "treatmentStartingDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "treatmentEndingDate", dateFormat = "dd-MM-yyyy"),
    })
    IngredientDTO toDTO(Ingredient ingredient);

    Collection<IngredientDTO> toDTOs(Collection<Ingredient> ingredients);

    @Mappings({
            @Mapping(target = "treatmentStartingDate", dateFormat = "dd-MM-yyyy"),
            @Mapping(target = "treatmentEndingDate", dateFormat = "dd-MM-yyyy"),
    })
    Ingredient toEntity(IngredientDTO ingredientDTO);

    Collection<Ingredient> toEntities(Collection<IngredientDTO> ingredientDTOS);

}
