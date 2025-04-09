package org.example.mealmatesapi.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(nullable = false)
    private String category_name;

    private String picture;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String desc;

    private Integer cook_time; //in minutes

    @ElementCollection
    private List<String> ingredients;

    private List<String> procedure;

    @Column(nullable = false)
    private Integer total_number_of_likes;

    @Column(nullable = false)
    private Integer total_number_of_bookmarks;

    public Recipe(User author, String category_name, String title, String desc, List<String> ingredients, List<String> procedure, Integer cook_time) {
        this.author = author;
        this.category_name = category_name;
        this.title = title;
        this.desc = desc;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCookTime() {
        return cook_time;
    }

    public void setCookTime(Integer cook_time) {
        this.cook_time = cook_time;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getProcedure() {
        return procedure;
    }

    public void setProcedure(List<String> procedure) {
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
