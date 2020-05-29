package com.example.todoapp.controllers;

import javax.validation.Valid;

import com.example.todoapp.models.Recipe;
import com.example.todoapp.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
//        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return recipeRepository.findAll();
    }

    @PostMapping("/recipes/create")
    public List<Recipe> createRecipe(@Valid @RequestBody List<Recipe> recipe) {
        return recipeRepository.saveAll(recipe);
    }

    @GetMapping(value = "/recipes/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") String id) {
        return recipeRepository.findById(id)
                .map(recipe -> ResponseEntity.ok().body(recipe))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/recipes/{id}")
    public ResponseEntity<Recipe> updateRecipes(@PathVariable("id") String id,
                                                @Valid @RequestBody Recipe recipe) {
        return recipeRepository.findById(id)
                .map(recipeData -> {
                    recipeData.setTitle(recipe.getTitle());
                    Recipe updatedRecipe = recipeRepository.save(recipeData);
                    return ResponseEntity.ok().body(updatedRecipe);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/recipes/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable("id") String id) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping(value = "/recipes/{ingredient}")
//    public List<Ingredient> getIngredients(@PathVariable("ingredient") String ingredient) {
//        this.recipeRepository.findAll()
//                .stream()
//                .map(Recipe::getIngredients)
//                .filter();
//    }
}
