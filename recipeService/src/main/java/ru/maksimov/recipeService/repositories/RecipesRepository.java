package ru.maksimov.recipeService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.recipeService.models.Recipe;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe, Integer> {
}
