package ru.maksimov.recipeService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.maksimov.recipeService.models.Ingredient;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class UpdateRecipeDto {
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Description should not be empty")
    private String description;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime cookingTime;
    private int portions;
    @NotEmpty(message = "Photo should not be empty")
    private String photo;
    private List<Ingredient> ingredients;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(LocalTime cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
