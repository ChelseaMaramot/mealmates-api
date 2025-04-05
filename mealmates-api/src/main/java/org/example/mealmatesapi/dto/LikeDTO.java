package org.example.mealmatesapi.dto;

public class LikeDTO {
    private Long id;
    private Long userId;
    private Long recipeId;

    private LikeDTO(){}
    public LikeDTO(Long userId, Long recipeId){
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

}
