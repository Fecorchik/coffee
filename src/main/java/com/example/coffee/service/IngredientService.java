package com.example.coffee.service;

import com.example.coffee.domain.dto.IngredientDto;
import com.example.coffee.domain.dto.request.RequestIngredient;
import com.example.coffee.domain.entity.Ingredient;
import com.example.coffee.domain.mapper.IngredientMapper;
import com.example.coffee.exception.CoffeeMakerException;
import com.example.coffee.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public List<IngredientDto> getAllIngredient(){
       return ingredientRepository.findAll()
                .stream()
                .map(ingredientMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public IngredientDto updateIngredient(RequestIngredient i){
        Ingredient ingredient = ingredientRepository.findByName(i.getName()).orElseThrow(() -> new CoffeeMakerException("Ингредиент не найден: %s", HttpStatus.NOT_FOUND, i.getName()));

        if(ingredient.getCount() + i.getCount() <= ingredient.getMax()){
            ingredient.setCount(ingredient.getCount() + i.getCount());
            ingredientRepository.save(ingredient);
        }else{
            throw new CoffeeMakerException("Слишком много %s", HttpStatus.BAD_REQUEST, i.getName());
        }

        return ingredientMapper.mapToDto(ingredient);
    }

}
