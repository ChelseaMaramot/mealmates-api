package org.example.mealmatesapi.dto;

import java.util.List;

public class RecipeDTO {
    private Long id;
    private String author;
    private String title;
    private String desc;
    private Integer likes;
    private List<String> ingredients;
    private Integer cookingTime;
    private List<String> instructions;



    public RecipeDTO(String author, String title, String desc, Integer likes,  List<String> ingredients, Integer cookingTime, List<String> instructions){
        this.author = author;
        this.desc = desc;
        this.title = title;
        this.likes = likes;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
        this.instructions = instructions;
    }

    public Long getId(){
        return id;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDesc(){
        return this.desc;
    }

    public Integer getLikes(){
        return likes;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public void setLikes(Integer likes){
        this.likes = likes;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }
    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
