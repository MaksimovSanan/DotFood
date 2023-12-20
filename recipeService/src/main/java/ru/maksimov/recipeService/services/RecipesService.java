package ru.maksimov.recipeService.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maksimov.recipeService.dto.RecipeDto;
import ru.maksimov.recipeService.dto.RecipeWithIngredientsDto;
import ru.maksimov.recipeService.models.Recipe;
import ru.maksimov.recipeService.repositories.RecipesRepository;
import ru.maksimov.recipeService.util.exceptions.RecipeNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RecipesService {
    private final RecipesRepository recipesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RecipesService(RecipesRepository recipesRepository, ModelMapper modelMapper) {
        this.recipesRepository = recipesRepository;
        this.modelMapper = modelMapper;
    }

//    public List<Recipe> findAll() {
//        return recipesRepository.findAll();
//    }

    public List<RecipeDto> findAllRecipes() {
        List<Recipe> recipes = recipesRepository.findAll();
        return recipes.stream()
                .map(this::mapToRecipeDto)
                .collect(Collectors.toList());
    }

    private RecipeDto mapToRecipeDto(Recipe recipe) {
        RecipeDto recipeDto = modelMapper.map(recipe, RecipeDto.class);
        return recipeDto;
    }

    public RecipeWithIngredientsDto findRecipeByIdWithIngredients(int id) {
        Optional<Recipe> recipeOptional = recipesRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();

            RecipeWithIngredientsDto recipeWithIngredientsDto = modelMapper.map(recipe, RecipeWithIngredientsDto.class);

            return recipeWithIngredientsDto;
        } else {
            throw new RecipeNotFoundException();
        }
    }

    public Recipe findById(int id) {
        Optional<Recipe> recipe = recipesRepository.findById(id);
        return recipe.orElseThrow(RecipeNotFoundException::new);
    }
}
