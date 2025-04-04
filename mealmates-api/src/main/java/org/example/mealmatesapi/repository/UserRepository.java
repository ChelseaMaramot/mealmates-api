package org.example.mealmatesapi.repository;

import org.example.mealmatesapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
