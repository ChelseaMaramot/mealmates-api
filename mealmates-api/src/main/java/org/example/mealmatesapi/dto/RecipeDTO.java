package org.example.mealmatesapi.dto;

public class RecipeDTO {
    private Long id;
    private String author;
    private String title;
    private String desc;
    private Integer likes;


    public RecipeDTO(String author, String title, String desc){
        this.author = author;
        this.desc = desc;
        this.title = title;
    }
}
