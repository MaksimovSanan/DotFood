package ru.maksimov.recipeService.controllers;

import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.recipeService.dto.NewRecipeDto;
import ru.maksimov.recipeService.dto.RecipeDto;
import ru.maksimov.recipeService.dto.RecipeWithIngredientsDto;
import ru.maksimov.recipeService.dto.UpdateRecipeDto;
import ru.maksimov.recipeService.models.Recipe;
import ru.maksimov.recipeService.services.RecipesService;
import ru.maksimov.recipeService.util.RecipeErrorResponse;
import ru.maksimov.recipeService.util.exceptions.RecipeNotCreatedException;
import ru.maksimov.recipeService.util.exceptions.RecipeNotFoundException;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipesService recipesService;
    private final ModelMapper modelMapper;

    @Autowired
    public RecipesController(RecipesService recipesService, ModelMapper modelMapper) {
        this.recipesService = recipesService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public List<RecipeDto> findAllRecipes() {
        return recipesService.findAll().stream().map(this::mapToRecipeDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RecipeWithIngredientsDto findOneRecipe(@PathVariable("id") int id) {
        return modelMapper.map(recipesService.findById(id), RecipeWithIngredientsDto.class);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid NewRecipeDto recipe,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new RecipeNotCreatedException(errorMsg.toString());
        }
        recipesService.save(modelMapper.map(recipe, Recipe.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        Recipe recipe = recipesService.findById(id);
        recipesService.delete(recipe);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid UpdateRecipeDto recipe,
                                             BindingResult bindingResult,
                                             @PathVariable("id") int id) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new RecipeNotCreatedException(errorMsg.toString());
        }
        Recipe recipeToBeUpdate = modelMapper.map(recipe, Recipe.class);
        recipeToBeUpdate.setId(id);
        recipesService.update(recipeToBeUpdate);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private RecipeDto mapToRecipeDto(Recipe recipe) {
        RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
        return recipeDto;
    }

    @ExceptionHandler
    public ResponseEntity<RecipeErrorResponse> handleException(RecipeNotFoundException recipeNotFoundException) {
        RecipeErrorResponse recipeErrorResponse = new RecipeErrorResponse(
                "Recipe with this id was not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(recipeErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RecipeErrorResponse> handleException(RecipeNotCreatedException recipeNotCreatedException){
        RecipeErrorResponse recipeErrorResponse = new RecipeErrorResponse(
                recipeNotCreatedException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(recipeErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
