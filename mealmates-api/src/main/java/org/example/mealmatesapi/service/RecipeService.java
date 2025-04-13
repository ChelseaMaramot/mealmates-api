package org.example.mealmatesapi.service;

import org.example.mealmatesapi.dto.CategoryDTO;
import org.example.mealmatesapi.dto.RecipeDTO;
import org.example.mealmatesapi.dto.UserDTO;
import org.example.mealmatesapi.model.Category;
import org.example.mealmatesapi.model.Recipe;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.payload.request.RecipeCreateRequest;
import org.example.mealmatesapi.repository.CategoryRepository;
import org.example.mealmatesapi.repository.RecipeRepository;
import org.example.mealmatesapi.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


// contains business logic
// uses repo to perform CRUD operations
// handles conversion between Entity and DTO

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository, CategoryRepository categoryRepository){
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    private RecipeDTO mapToDTO(Recipe recipe){
        CategoryDTO categoryDTO = new CategoryDTO(recipe.getCategory().getId(), recipe.getCategory().getName());
        return new RecipeDTO(
                recipe.getId(),
                recipe.getAuthor().getId(),
                categoryDTO,
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getPicture(),
                recipe.getTotalNumberOfLikes(),
                recipe.getTotalNumberOfBookmarks(),
                recipe.getCookTime(),
                recipe.getIngredients(),
                recipe.getProcedure()
        );
    }

    private Recipe mapToEntity(RecipeDTO recipeDTO) {
        User author = userRepository.findById(recipeDTO.getAuthorId()) .orElseThrow(() -> new RuntimeException("Author not found with ID: " + recipeDTO.getAuthorId()));
        Category category = categoryRepository.findByName(recipeDTO.getCategory().getName());
        return new Recipe(author,
                category,
                recipeDTO.getPicture(),
                recipeDTO.getTitle(),
                recipeDTO.getDescription(),
                recipeDTO.getIngredients(),
                recipeDTO.getProcedure(),
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
    public RecipeDTO createRecipe(RecipeCreateRequest request, String author_email) {
        System.out.println("Creating recipe..., " + request);
        MultipartFile file = request.getPicture();
        String picture = null;

        if (file != null && !file.isEmpty()) {
            picture = file.getOriginalFilename();
        } else {
            System.out.println("No picture uploaded.");
        }

//        String picture = file.getOriginalFilename();


        System.out.println("Category: " + request.getCategory().getName());
        System.out.println("Picture: "+picture);

        User user = userRepository.findByEmail(author_email);


        Recipe recipe = new Recipe();
        recipe.setTitle(request.getTitle());
        recipe.setDescription(request.getDesc());
        recipe.setCookTime(request.getCook_time());
        recipe.setIngredients(request.getIngredients());
        recipe.setProcedure(request.getProcedure());
        recipe.setPicture(picture);
        recipe.setAuthor(user);

        System.out.println("Cooking time: "+ recipe.getCookTime());


        System.out.println("looking for category...");
        Category category = categoryRepository.findByName(request.getCategory().getName());

        if (category == null) {
            category = new Category(request.getCategory().getName());
            category = categoryRepository.save(category);
            System.out.println("nEW Category ID: " + category.getId());
        }

        System.out.println("Found: "+ category);
        System.out.println("Category ID: " + category.getId());
        recipe.setCategory(category);

        Recipe savedRecipe = recipeRepository.save(recipe);
        System.out.println("recipe saved success");

        return mapToDTO(savedRecipe);
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
            existingRecipe.setProcedure(recipeDTO.getProcedure());
            existingRecipe.setIngredients(recipeDTO.getIngredients());
            existingRecipe.setCookTime(recipeDTO.getCookingTime());

            Recipe updatedRecipe = recipeRepository.save(existingRecipe);
            return mapToDTO(updatedRecipe);
        }else{
            throw new RuntimeException("Recipe not found with id: " + id);
        }
    }


}
