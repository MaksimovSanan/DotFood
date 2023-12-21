package ru.maksimov.recipeService.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maksimov.recipeService.models.Recipe;
import ru.maksimov.recipeService.repositories.RecipesRepository;
import ru.maksimov.recipeService.util.exceptions.RecipeNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RecipesService {
    private final RecipesRepository recipesRepository;

    @Autowired
    public RecipesService(RecipesRepository recipesRepository, ModelMapper modelMapper) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = recipesRepository.findAll();
        return recipes;
    }

    public Recipe findById(int id) {
        Optional<Recipe> recipeOptional = recipesRepository.findById(id);

        return recipeOptional.orElseThrow(RecipeNotFoundException::new);
    }

    @Transactional
    public void save(Recipe recipe) {
        enrichRecipe(recipe);
        recipesRepository.save(recipe);
    }

    @Transactional
    public void delete(Recipe recipe) {
        recipesRepository.delete(recipe);
    }

    @Transactional
    public void update(Recipe recipe) {
        Recipe recipeToBeUpdate = this.findById(recipe.getId());
        recipeToBeUpdate.setTitle(recipe.getTitle());
        recipeToBeUpdate.setDescription(recipe.getDescription());
        recipeToBeUpdate.setCookingTime(recipe.getCookingTime());
        recipeToBeUpdate.setPortions(recipe.getPortions());
        recipeToBeUpdate.setPhoto(recipe.getPhoto());
        recipeToBeUpdate.setIngredients(recipe.getIngredients());
        recipeToBeUpdate.setStatus(999);
    }

    private void enrichRecipe(Recipe recipe) {
        recipe.setPostDate(LocalDateTime.now());
        recipe.setStatus(666);
    }

}













