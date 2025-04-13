package org.example.mealmatesapi.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.example.mealmatesapi.dto.CategoryDTO;
import org.example.mealmatesapi.dto.RecipeDTO;
import org.example.mealmatesapi.dto.UserDTO;
import org.example.mealmatesapi.model.Recipe;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.payload.request.RecipeCreateRequest;
import org.example.mealmatesapi.repository.RecipeRepository;
import org.example.mealmatesapi.service.RecipeService;
import org.example.mealmatesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;


    // Get recipes by name
    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getRecipesByName(@RequestParam String author_username){
        System.out.println("Getting recipe for "+author_username);
        List<RecipeDTO> recipes = recipeService.getRecipesByAuthor(author_username);
        System.out.println(recipes);
        return ResponseEntity.ok(recipes);
    }

    // Get recipes by id
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long id){
        RecipeDTO recipe = recipeService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    // Create a new recipe
    @PostMapping(value = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<RecipeDTO> createRecipe(@ModelAttribute RecipeCreateRequest recipeCreateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        RecipeDTO createdRecipe = recipeService.createRecipe(recipeCreateRequest, email);
        System.out.println("CREATED: "+createdRecipe);
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
