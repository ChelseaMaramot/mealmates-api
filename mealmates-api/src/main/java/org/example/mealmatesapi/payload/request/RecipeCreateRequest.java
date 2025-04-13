package org.example.mealmatesapi.payload.request;

import org.example.mealmatesapi.dto.CategoryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RecipeCreateRequest {
    private CategoryDTO category;

    private MultipartFile picture;

    private String title;
    private String desc;
    private String cook_time;
    private String ingredients;
    private String procedure;

    // Getters
    public CategoryDTO getCategory() {
        return category;
    }

    public MultipartFile getPicture(){
        return this.picture;
    }


    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCook_time() {
        return cook_time;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getProcedure() {
        return procedure;
    }

    // Setters
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCook_time(String cook_time) {
        this.cook_time = cook_time;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public void setPicture(MultipartFile picture){
        this.picture = picture;
    }
}
