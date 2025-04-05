package org.example.mealmatesapi.repository;

import org.example.mealmatesapi.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// interface for performing db operations
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{
    List<Recipe> findByTitleContaining(String title);

}
