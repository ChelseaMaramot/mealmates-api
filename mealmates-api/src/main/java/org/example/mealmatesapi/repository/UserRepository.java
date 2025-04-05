package org.example.mealmatesapi.repository;

import org.example.mealmatesapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
    User findByUsername(String name);
    boolean existsByUsername(String username);
}
