package org.example.mealmatesapi.service;

import org.example.mealmatesapi.dto.LikeDTO;
import org.example.mealmatesapi.model.Like;
import org.example.mealmatesapi.model.Recipe;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.repository.LikeRepository;
import org.example.mealmatesapi.repository.RecipeRepository;
import org.example.mealmatesapi.repository.UserRepository;
import org.hibernate.boot.model.process.internal.UserTypeResolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public LikeService(
            LikeRepository likeRepository,
            UserRepository userRepository,
            RecipeRepository recipeRepository
    ) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }


    public LikeDTO mapToDTO(Like like) {
        return new LikeDTO(like.getUser().getId(), like.getRecipe().getId());
    }

    public Like mapToEntity(LikeDTO likeDTO) {
        User user = userRepository.findById(likeDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(likeDTO.getRecipeId()).orElseThrow(() -> new RuntimeException("Recipe not found"));
        return new Like(user, recipe);
    }

    // Like recipe
    public LikeDTO likeRecipe(LikeDTO likeDTO) {
        Like savedLike = likeRepository.save(mapToEntity(likeDTO));
        return mapToDTO(savedLike);
    }

    // Remove Like
    public void removeLike(Long userId, Long recipeId) {
        Like like = likeRepository.findByUserIdAndRecipeId(userId, recipeId)
                .orElseThrow(() -> new RuntimeException("Like not found"));
        likeRepository.delete(like);
    }

    public Long getNumberOfLikes(Long recipeId){
        return likeRepository.countByRecipe(recipeId);
    }
}
