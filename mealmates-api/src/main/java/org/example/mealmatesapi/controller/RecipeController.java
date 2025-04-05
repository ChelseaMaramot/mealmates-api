package org.example.mealmatesapi.controller;

import org.apache.coyote.Response;
import org.example.mealmatesapi.dto.RecipeDTO;
import org.example.mealmatesapi.model.Recipe;
import org.example.mealmatesapi.repository.RecipeRepository;
import org.example.mealmatesapi.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;


    // Get recipes by name
    @GetMapping("/{name}")
    public ResponseEntity<List<RecipeDTO>> getRecipesByName(@PathVariable String name){
        List<RecipeDTO> recipes = recipeService.getRecipesByTitle(name);
        return ResponseEntity.ok(recipes);
    }

    // Get recipes by id
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id){
        RecipeDTO recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    // Create a new recipe
    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@RequestBody RecipeDTO recipeDTO){
        RecipeDTO createdRecipe = recipeService.createRecipe(recipeDTO);
        return ResponseEntity.ok(createdRecipe);
    }

    // Update a recipe
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO){
        RecipeDTO updatedRecipe = recipeService.updateRecipe(id, recipeDTO);
        return ResponseEntity.ok(updatedRecipe);
    }

    // Delete a Recipe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build(); // send a 204 response for no content -> success nothing to return
    }
}
