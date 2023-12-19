package ru.maksimov.recipeService.services;

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

    @Autowired
    public RecipesService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipe> findAll() {
        return recipesRepository.findAll();
    }

    public List<RecipeDto> findAllRecipes() {
        List<Recipe> recipes = recipesRepository.findAll();
        return recipes.stream()
                .map(this::mapToRecipeDto)
                .collect(Collectors.toList());
    }

    private RecipeDto mapToRecipeDto(Recipe recipe) {
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId(recipe.getId());
        recipeDto.setTitle(recipe.getTitle());
        recipeDto.setDescription(recipe.getDescription());
        recipeDto.setCookingTime(recipe.getCookingTime());
        recipeDto.setRating(recipe.getRating());
        recipeDto.setPhoto(recipe.getPhoto());
        recipeDto.setStatus(recipe.getStatus());
        return recipeDto;
    }

    public RecipeWithIngredientsDto findRecipeByIdWithIngredients(int id) {
        Optional<Recipe> recipeOptional = recipesRepository.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();

            // Создаем RecipeWithIngredientsDto
            RecipeWithIngredientsDto recipeWithIngredientsDto = new RecipeWithIngredientsDto();
            recipeWithIngredientsDto.setId(recipe.getId());
            recipeWithIngredientsDto.setTitle(recipe.getTitle());
            recipeWithIngredientsDto.setDescription(recipe.getDescription());
            recipeWithIngredientsDto.setCookingTime(recipe.getCookingTime());
            recipeWithIngredientsDto.setRating(recipe.getRating());
            recipeWithIngredientsDto.setPhoto(recipe.getPhoto());
            recipeWithIngredientsDto.setStatus(recipe.getStatus());

            // Заполняем ингредиенты для рецепта
            recipeWithIngredientsDto.setIngredients(recipe.getIngredients());

            return recipeWithIngredientsDto;
        } else {
            // Обработка случая, когда рецепт с указанным id не найден
            throw new RecipeNotFoundException();
        }
    }

    public Recipe findById(int id) {
        Optional<Recipe> recipe = recipesRepository.findById(id);
        return recipe.orElseThrow(RecipeNotFoundException::new);
    }
}
