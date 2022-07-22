package com.example.coffee.service;

import com.example.coffee.domain.dto.request.RequestRecipe;
import com.example.coffee.domain.entity.Recipe;
import com.example.coffee.domain.entity.Resource;
import com.example.coffee.exception.CoffeeMakerException;
import com.example.coffee.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CoffeeMakerService {
    private final RecipeRepository recipeRepository;

    @Transactional
    public void makeCoffee(RequestRecipe r){
        String nameRecipe = r.getRecipeName();

        Optional<Recipe> recipeOptional = recipeRepository.findByName(nameRecipe);
        Recipe recipe = recipeOptional.orElseThrow(() -> new CoffeeMakerException("Рецепт не найден: %s", HttpStatus.NOT_FOUND, nameRecipe));

        for (Resource resource: recipe.getResources()) {
            if(resource.getIngredient().getCount() < resource.getCount()){
               throw new CoffeeMakerException("Не хватает компонента в машинке: %s", HttpStatus.BAD_REQUEST, resource.getIngredient().getName());
            }else{
                resource.getIngredient().setCount(resource.getIngredient().getCount() - resource.getCount());
            }
        }

        recipeRepository.save(recipe);
    }

}
