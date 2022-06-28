package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.DTO.IngredientDTO;
import com.doctorspolis.backend.model.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Collection;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface IngredientMapper {

    IngredientDTO toDTO(Ingredient ingredient);

    Collection<IngredientDTO> toDTOs(Collection<Ingredient> ingredients);

    Ingredient toEntity(IngredientDTO ingredientDTO);

    Collection<Ingredient> toEntities(Collection<IngredientDTO> ingredientDTOS);

}
