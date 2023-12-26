package ru.nova.client.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nova.client.model.Ingredient;
import ru.nova.client.service.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/admin/ingredients")
@RequiredArgsConstructor
@Slf4j
public class ManageIngredientsController {

    private final IngredientService ingredientService;

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('ROLE_USER')")
//    @Secured("ADMIN")

    public Iterable<Ingredient> ingredientsAdmin(Model model) {
        log.info("GET - /admin/ingredients");
//        model.addAttribute("ingredients", ingredientService.findAll());
        return ingredientService.findAll();
    }

    @PostMapping
    public String addIngredient(Ingredient ingredient) {
        log.info("POST - /admin/ingredients");
        ingredientService.addIngredient(ingredient);
        return "redirect:/admin/ingredients";
    }

}
