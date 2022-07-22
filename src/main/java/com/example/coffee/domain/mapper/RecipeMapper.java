package com.example.coffee.domain.mapper;

import com.example.coffee.domain.dto.RecipeDto;
import com.example.coffee.domain.entity.Recipe;
import org.mapstruct.Mapper;

@Mapper
public interface RecipeMapper {
    RecipeDto mapToDto(Recipe recipe);
}
