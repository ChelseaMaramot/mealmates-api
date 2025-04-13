package org.example.mealmatesapi.model;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = true)
    private String  picture;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String description;

    private String cook_time; //in minutes

    @Column(name = "ingredients")
    private String ingredients;
    private String procedure;

    @Column(nullable = false)
    private Integer total_number_of_likes = 0;

    @Column(nullable = false)
    private Integer total_number_of_bookmarks = 0;
    
    public Recipe(){};

    public Recipe(User author, Category category, String picture, String title, String description, String ingredients, String procedure, String cook_time) {
        this.author = author;
        this.category = category;
        this.picture = picture;
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.procedure = procedure;
        this.cook_time = cook_time;
        this.total_number_of_likes = 0;
        this.total_number_of_bookmarks = 0;
    }

    //GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCookTime() {
        return cook_time;
    }

    public void setCookTime(String cook_time) {
        this.cook_time = cook_time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public Integer getTotalNumberOfLikes() {
        return total_number_of_likes;
    }

    public void setTotalNumberOfLikes(Integer total_number_of_likes) {
        this.total_number_of_likes = total_number_of_likes;
    }

    public Integer getTotalNumberOfBookmarks() {
        return total_number_of_bookmarks;
    }

    public void setTotalNumberOfBookmarks(Integer total_number_of_bookmarks) {
        this.total_number_of_bookmarks = total_number_of_bookmarks;
    }

}
