package com.example.coffee.controller;

import com.example.coffee.domain.dto.IngredientDto;
import com.example.coffee.domain.dto.RecipeDto;
import com.example.coffee.domain.dto.request.RequestIngredient;
import com.example.coffee.domain.dto.request.RequestRecipe;
import com.example.coffee.service.CoffeeMakerService;
import com.example.coffee.service.IngredientService;
import com.example.coffee.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coffee")
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeMakerService coffeeMakerService;
    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<HttpStatus> makeCoffee(@RequestBody RequestRecipe recipe){
        coffeeMakerService.makeCoffee(recipe);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientDto>> getIngredient(){
         return ResponseEntity.ok(ingredientService.getAllIngredient());
    }

    @PutMapping("/ingredients")
    public ResponseEntity<IngredientDto> updateIngredient(@RequestBody RequestIngredient ingredient){
        return ResponseEntity.ok(ingredientService.updateIngredient(ingredient));
    }

    @GetMapping("/recipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipe(){
        return ResponseEntity.ok(recipeService.getAll());
    }
}
