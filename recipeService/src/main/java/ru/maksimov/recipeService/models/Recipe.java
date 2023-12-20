package ru.maksimov.recipeService.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "cooking_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime cookingTime;
    @Column(name = "rating")
    private Float rating;
    @Column(name = "photo")
    private String photo;
    @Column(name = "status")
    private int status;

    @ManyToMany
    @JoinTable(
            name = "recipes_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonManagedReference
//    @JsonIgnore
    List<Ingredient> ingredients;

    public Recipe() {
    }

    public Recipe(String title, String description, LocalTime cookingTime, float rating, String photo, int status, List<Ingredient> ingredients) {
        this.title = title;
        this.description = description;
        this.cookingTime = cookingTime;
        this.rating = rating;
        this.photo = photo;
        this.status = status;
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if(ingredients == null) {
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
        ingredient.addRecipe(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cookingTime=" + cookingTime +
                ", rating=" + rating +
                ", photo='" + photo + '\'' +
                ", status=" + status +
                ", ingredients=" + ingredients +
                '}';
    }
}