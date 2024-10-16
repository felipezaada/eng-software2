package com.example.Cadastro.repositories;

import com.example.Cadastro.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByLoginContaining(String name);
}