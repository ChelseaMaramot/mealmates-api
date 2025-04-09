package org.example.mealmatesapi.dto;

import java.util.List;

public class RecipeDTO {

    private Long id;
    private String authorUsername;
    private String category;
    private String title;
    private String description;
    private String picture;
    private Integer likes;
    private Integer bookmarks;
    private Integer cookingTime;
    private List<String> ingredients;
    private List<String> instructions;

    public RecipeDTO() {
    }

    public RecipeDTO(Long id, String authorUsername, String category, String title, String description, String picture,
                     Integer likes, Integer bookmarks, Integer cookingTime,
                     List<String> ingredients, List<String> instructions) {
        this.id = id;
        this.authorUsername = authorUsername;
        this.category = category;
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.likes = likes;
        this.bookmarks = bookmarks;
        this.cookingTime = cookingTime;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getCategory() {
        return category;
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

    public Integer getCookingTime() {
        return cookingTime;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setBookmarks(Integer bookmarks) {
        this.bookmarks = bookmarks;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
