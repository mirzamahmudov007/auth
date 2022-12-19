package com.example.javaProject1.repository;

import com.example.javaProject1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

}
