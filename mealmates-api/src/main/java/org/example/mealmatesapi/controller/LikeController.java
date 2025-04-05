package org.example.mealmatesapi.controller;

import org.apache.coyote.Response;
import org.example.mealmatesapi.dto.LikeDTO;
import org.example.mealmatesapi.model.Like;
import org.example.mealmatesapi.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    //getLikes
    @GetMapping("/count")
    public ResponseEntity<Long> getNumberOfLikes(@RequestParam Long recipeId){
        Long numLikes =  likeService.getNumberOfLikes(recipeId);
        return ResponseEntity.ok(numLikes);
    }

    //like
    @PostMapping
    public ResponseEntity<LikeDTO> likeRecipe(@RequestBody LikeDTO likeDTO){
        LikeDTO likedRecipe = likeService.likeRecipe(likeDTO);
        return ResponseEntity.ok(likedRecipe);
    }

    //removeLikes
    @DeleteMapping
    public ResponseEntity<Void> removeLike(@RequestParam Long userId, @RequestParam Long recipeId){
        likeService.removeLike(userId, recipeId);
        return ResponseEntity.noContent().build();
    }

}
