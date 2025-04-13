package org.example.mealmatesapi.dto;

import org.example.mealmatesapi.model.Category;
import org.example.mealmatesapi.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RecipeDTO {

    private Long id;
    private CategoryDTO category;
    private Long author;
    private String title;
    private String description;
    private String picture;
    private Integer likes;
    private Integer bookmarks;
    private String cookingTime;
    private String ingredients;
    private String procedure;

    public RecipeDTO() {
    }

    public RecipeDTO(Long id, Long author, CategoryDTO category, String title, String description, String picture,
                     Integer likes, Integer bookmarks, String cookingTime,
                     String ingredients, String procedure) {
        this.id = id;
        this.author = author;
        this.category = category;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.likes = 0;
        this.bookmarks = 0;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.procedure = procedure;
    }

//    public static RecipeDTO from(Recipe recipe) {
//        RecipeDTO dto = new RecipeDTO();
//
//        dto.setId(recipe.getId());
//        dto.setTitle(recipe.getTitle());
//        dto.setDescription(recipe.getDescription());
//        dto.setCookingTime(recipe.getCookTime());
//        dto.setIngredients(recipe.getIngredients());
//        dto.setProcedure(recipe.getProcedure());
//        dto.setPicture(recipe.getPicture());
//        dto.setAuthorId(recipe.getId());
//
//
//
//        if (recipe.getCategory() != null) {
//            CategoryDTO categoryDTO = new CategoryDTO();
//            categoryDTO.setName(recipe.getCategory().getName());
//            categoryDTO.setId(recipe.getCategory().getId());
//            dto.setCategory(categoryDTO);
//        }
//        return dto;
//    }


    // Getters

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return author;
    }

    public CategoryDTO getCategory() {
        return this.category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getBookmarks() {
        return bookmarks;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProcedure() {
        return procedure;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorId(long authorId) {
        this.author = authorId;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String  picture) {
        this.picture = picture;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setBookmarks(Integer bookmarks) {
        this.bookmarks = bookmarks;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
}
