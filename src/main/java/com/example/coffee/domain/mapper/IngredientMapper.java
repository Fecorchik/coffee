package com.example.coffee.domain.mapper;

import com.example.coffee.domain.dto.IngredientDto;
import com.example.coffee.domain.entity.Ingredient;
import org.mapstruct.Mapper;

@Mapper
public interface IngredientMapper {
    IngredientDto mapToDto(Ingredient ingredient);
}
