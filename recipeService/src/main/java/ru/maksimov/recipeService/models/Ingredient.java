package ru.maksimov.recipeService.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private Float price;
    @Column(name = "photo")
    private String photo;

    @ManyToMany(mappedBy = "ingredients")
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    @JsonBackReference
    @JsonIgnore
    List<Recipe> recipes;

    public Ingredient() {
    }

    public Ingredient(String title, float price, String photo, List<Recipe> recipes) {
        this.title = title;
        this.price = price;
        this.photo = photo;
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        if(recipes == null) {
            recipes = new ArrayList<>();
        }
        recipes.add(recipe);
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                '}';
    }
}
