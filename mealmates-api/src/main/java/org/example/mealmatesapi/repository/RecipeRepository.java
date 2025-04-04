package org.example.mealmatesapi.repository;

import org.example.mealmatesapi.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

// interface for performing db operations
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
