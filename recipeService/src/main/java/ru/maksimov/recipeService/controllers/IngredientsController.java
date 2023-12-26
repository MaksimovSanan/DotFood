package ru.maksimov.recipeService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.recipeService.models.Ingredient;
import ru.maksimov.recipeService.services.IngredientsService;
import ru.maksimov.recipeService.util.IngredientErrorResponse;
import ru.maksimov.recipeService.util.exceptions.IngredietNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    IngredientsService ingredientsService;

    @Autowired
    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public List<Ingredient> findAll() {
        System.out.println("------------------------------324323");
        return ingredientsService.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient findOne(@PathVariable("id") int id) {
        return ingredientsService.findById(id);
    }

    @ExceptionHandler
    public ResponseEntity<IngredientErrorResponse> handleException(IngredietNotFoundException ingredietNotFoundException) {
        IngredientErrorResponse ingredientErrorResponse = new IngredientErrorResponse(
                "Ingredient with this id was not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(ingredientErrorResponse, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public String test(){
        return "Testtesttes";
    }
}
