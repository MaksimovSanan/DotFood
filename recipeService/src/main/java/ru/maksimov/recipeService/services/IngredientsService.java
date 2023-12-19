package ru.maksimov.recipeService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maksimov.recipeService.models.Ingredient;
import ru.maksimov.recipeService.repositories.IngredientsRepository;
import ru.maksimov.recipeService.util.exceptions.IngredietNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<Ingredient> findAll() {
        return ingredientsRepository.findAll();
    }

    public Ingredient findById(int id) {
        Optional<Ingredient> ingredient = ingredientsRepository.findById(id);
        return ingredient.orElseThrow(IngredietNotFoundException::new);
    }
}
