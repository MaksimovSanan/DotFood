package ru.maksimov.uiService.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.maksimov.uiService.dto.NewRecipe;
import ru.maksimov.uiService.dto.Recipe;
import ru.maksimov.uiService.dto.RecipeWithIngredients;
import ru.maksimov.uiService.util.HtmlFormManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("recipes")
public class RecipesController {

    @GetMapping
    public String index(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:8080/recipes";
        String response = restTemplate.getForObject(uri, String.class);
        System.out.println(response);
        List<Recipe> recipes = restTemplate.getForObject(uri, List.class);
        model.addAttribute("recipes", recipes);
        return HtmlFormManager.RecipesHtml.index;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:8080/recipes/" + id;
        String response = restTemplate.getForObject(uri, String.class);
        System.out.println(response);
        RecipeWithIngredients recipe = restTemplate.getForObject(uri, RecipeWithIngredients.class);
        model.addAttribute("recipe", recipe);
        return HtmlFormManager.RecipesHtml.show;
    }

    @GetMapping("/create")
    public String toCreate(@ModelAttribute("recipe")NewRecipe recipe) {
        return HtmlFormManager.RecipesHtml.newRecipe;
    }

    @PostMapping
    public String createRecipe(Model model, @ModelAttribute("recipe") @Valid NewRecipe recipe,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return HtmlFormManager.RecipesHtml.newRecipe;
        }
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/recipes";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NewRecipe> request = new HttpEntity<>(recipe, headers);
        restTemplate.postForObject(uri, request, String.class);

        return HtmlFormManager.RecipesHtml.redirectToIndex;
    }

    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable("id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/recipes/" + id;

        restTemplate.delete(uri);

        return HtmlFormManager.RecipesHtml.redirectToIndex;
    }

    @GetMapping("{id}/edit")
    public String toEditRecipe(@PathVariable("id") int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:8080/recipes/" + id;
        String response = restTemplate.getForObject(uri, String.class);
        System.out.println(response);
        RecipeWithIngredients recipe = restTemplate.getForObject(uri, RecipeWithIngredients.class);
        model.addAttribute("recipe", recipe);
        return HtmlFormManager.RecipesHtml.editRecipe;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("recipe") @Valid RecipeWithIngredients recipe,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HtmlFormManager.RecipesHtml.editRecipe;
        }

        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://localhost:8080/recipes/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RecipeWithIngredients> request = new HttpEntity<>(recipe, headers);

        restTemplate.put(uri, request, String.class);

        return HtmlFormManager.RecipesHtml.redirectToIndex;
    }
}
