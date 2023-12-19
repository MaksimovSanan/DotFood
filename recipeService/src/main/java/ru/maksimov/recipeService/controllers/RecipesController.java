package ru.maksimov.recipeService.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.recipeService.dto.RecipeDto;
import ru.maksimov.recipeService.dto.RecipeWithIngredientsDto;
import ru.maksimov.recipeService.models.Recipe;
import ru.maksimov.recipeService.services.RecipesService;
import ru.maksimov.recipeService.util.RecipeErrorResponse;
import ru.maksimov.recipeService.util.exceptions.RecipeNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    RecipesService recipesService;

    @Autowired
    public RecipesController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

//    @GetMapping
//    public List<Recipe> findAll() {
//        return recipesService.findAll();
//    }

    @GetMapping
    public List<RecipeDto> findAllRecipes() {
        // Ваш сервис должен возвращать List<RecipeDto> без ингредиентов
        return recipesService.findAllRecipes();
    }

//    @GetMapping("/{id}")
//    public Recipe findOne(@PathVariable("id") int id) {
//        return recipesService.findById(id);
//    }

    @GetMapping("/{id}")
    public RecipeWithIngredientsDto findOneRecipe(@PathVariable("id") int id) {
        // Ваш сервис должен возвращать RecipeWithIngredientsDto с ингредиентами
        return recipesService.findRecipeByIdWithIngredients(id);
    }

    @ExceptionHandler
    public ResponseEntity<RecipeErrorResponse> handleException(RecipeNotFoundException recipeNotFoundException) {
        RecipeErrorResponse recipeErrorResponse = new RecipeErrorResponse(
                "Recipe with this id was not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(recipeErrorResponse, HttpStatus.NOT_FOUND);
    }
}
