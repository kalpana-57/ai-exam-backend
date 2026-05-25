package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.Score;
import com.example.backend.repository.ScoreRepository;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*")
public class ScoreController {

    @Autowired
    private ScoreRepository repo;

    @PostMapping("/save")
    public Score saveScore(@RequestBody Score score) {
        return repo.save(score);
    }

    @GetMapping("/user/{username}")
    public List<Score> getUserScore(@PathVariable String username) {
        return repo.findByUsername(username);
    }
}