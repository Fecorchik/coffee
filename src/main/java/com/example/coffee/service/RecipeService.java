package com.example.coffee.service;

import com.example.coffee.domain.dto.RecipeDto;
import com.example.coffee.domain.mapper.RecipeMapper;
import com.example.coffee.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    public List<RecipeDto> getAll(){
        return recipeRepository.findAll()
                .stream()
                .map(recipeMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
