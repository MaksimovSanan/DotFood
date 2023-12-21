package ru.maksimov.uiService.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.maksimov.uiService.models.Recipe;
import ru.maksimov.uiService.models.RecipeWithIngredients;
import ru.maksimov.uiService.util.HtmlFormManager;

import java.util.List;

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
}
