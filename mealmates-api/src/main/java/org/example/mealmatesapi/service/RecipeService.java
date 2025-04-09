package org.example.mealmatesapi.service;

import org.example.mealmatesapi.dto.RecipeDTO;
import org.example.mealmatesapi.model.Recipe;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.repository.RecipeRepository;
import org.example.mealmatesapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


// contains business logic
// uses repo to perform CRUD operations
// handles conversion between Entity and DTO

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository){
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    private RecipeDTO mapToDTO(Recipe recipe){
        return new RecipeDTO(
                recipe.getId(),
                recipe.getAuthor().getUsername(),
                recipe.getCategory_name(),
                recipe.getTitle(),
                recipe.getDesc(),
                recipe.getPicture(),
                recipe.getTotalNumberOfLikes(),
                recipe.getTotalNumberOfBookmarks(),
                recipe.getCookTime(),
                recipe.getIngredients(),
                recipe.getProcedure()
        );
    }

    private Recipe mapToEntity(RecipeDTO recipeDTO) {
        User author = userRepository.findByUsername(recipeDTO.getAuthorUsername());
        return new Recipe(author,
                recipeDTO.getCategory(),
                recipeDTO.getTitle(),
                recipeDTO.getDescription(),
                recipeDTO.getIngredients(),
                recipeDTO.getInstructions(),
                recipeDTO.getCookingTime()
        );
    }

    // Get all recipes by name
    public List<RecipeDTO> getRecipesByTitle(String title){
        return recipeRepository.findByTitleContaining(title).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get single recipe by id
    public RecipeDTO getRecipeById(Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return mapToDTO(recipe.get());
        }else{
            throw new RuntimeException("Recipe not found with id: " + id);
        }
    }

    // Get all recipes by name
    public List<RecipeDTO> getRecipesByAuthor(String author_username){
        return recipeRepository.findByAuthor_Username(author_username).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Create a new recipe
    public RecipeDTO createRecipe(RecipeDTO recipeDTO){
        return mapToDTO(recipeRepository.save(mapToEntity(recipeDTO)));
    }

    // Delete a recipe
    public void deleteRecipe(Long id){
        if(recipeRepository.existsById(id)){
            recipeRepository.deleteById(id);
        }else{
            throw new RuntimeException("Recipe not found with id: " + id);
        }

    }
    // update a recipe
    public RecipeDTO updateRecipe(Long id, RecipeDTO recipeDTO){
        Optional<Recipe> existingRecipeOpt = recipeRepository.findById(id);
        if (existingRecipeOpt.isPresent()){
            Recipe existingRecipe = existingRecipeOpt.get();
            existingRecipe.setTitle(recipeDTO.getTitle());
            existingRecipe.setProcedure(recipeDTO.getInstructions());
            existingRecipe.setIngredients(recipeDTO.getIngredients());
            existingRecipe.setCookTime(recipeDTO.getCookingTime());

            Recipe updatedRecipe = recipeRepository.save(existingRecipe);
            return mapToDTO(updatedRecipe);
        }else{
            throw new RuntimeException("Recipe not found with id: " + id);
        }
    }
}
