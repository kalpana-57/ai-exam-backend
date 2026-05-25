package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.entity.Score;

public interface ScoreRepository extends JpaRepository<Score, Integer> {

    List<Score> findByUsername(String username);

}