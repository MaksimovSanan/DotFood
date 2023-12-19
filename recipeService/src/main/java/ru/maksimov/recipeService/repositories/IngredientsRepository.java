package ru.maksimov.recipeService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.recipeService.models.Ingredient;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Integer> {
}
