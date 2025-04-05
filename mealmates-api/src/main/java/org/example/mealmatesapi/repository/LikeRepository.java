package org.example.mealmatesapi.repository;

import org.example.mealmatesapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndRecipeId(Long userId, Long recipeId);
    Long countByRecipeId(Long recipeId);
}
