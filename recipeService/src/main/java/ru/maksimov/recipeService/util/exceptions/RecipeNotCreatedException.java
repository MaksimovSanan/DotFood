package ru.maksimov.recipeService.util.exceptions;

public class RecipeNotCreatedException extends RuntimeException{
    public RecipeNotCreatedException(String message) {
        super(message);
    }
}
