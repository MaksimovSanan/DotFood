package ru.nova.client.service;

import ru.nova.client.model.Ingredient;

import java.util.List;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);

}
