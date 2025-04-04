package org.example.mealmatesapi.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

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

    public Recipe() {}

    public Recipe(String author, String title, String desc){
        this.author = author;
        this.desc = desc;
        this.title = title;
    }

    //GETTERS AND SETTERS
}
