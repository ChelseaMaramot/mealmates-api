package org.example.mealmatesapi.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String desc;

    @Column(nullable = false)
    private Integer likes = 0;

    @ElementCollection
    private List<String> ingredients;

    private Integer cookingTime; //in minutes

    private List<String> instructions;

    public Recipe() {}

    public Recipe(String author, String title, String desc, List<String> ingredients, Integer cookingTime, List<String> instructions){
        this.author = author;
        this.desc = desc;
        this.title = title;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
        this.instructions = instructions;
    }

    //GETTERS AND SETTERS

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
    public void incrementLikes(){
        this.likes++;
    }
    public void decrementLikes(){
        this.likes--;
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
