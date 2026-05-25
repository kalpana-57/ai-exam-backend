package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entity.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Integer> {

    Attempt findByEmail(String email);
}