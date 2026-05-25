package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.Attempt;
import com.example.backend.repository.AttemptRepository;

@RestController
@RequestMapping("/api/attempts")
@CrossOrigin(origins = "*")
public class AttemptController {

    @Autowired
    private AttemptRepository repo;

    @PostMapping("/save-photo")
    public Attempt savePhoto(@RequestBody Attempt attempt) {
        Attempt existing = repo.findByEmail(attempt.getEmail());

        if (existing != null) {
            existing.setName(attempt.getName());
            existing.setCategory(attempt.getCategory());
            existing.setCapturedImage(attempt.getCapturedImage());
            existing.setStatus("STARTED");
            return repo.save(existing);
        }

        attempt.setStatus("STARTED");
        attempt.setScore(0);
        return repo.save(attempt);
    }

    @PostMapping("/update-result")
    public Attempt updateResult(@RequestBody Attempt attempt) {
        Attempt existing = repo.findByEmail(attempt.getEmail());

        if (existing != null) {
            existing.setScore(attempt.getScore());
            existing.setStatus(attempt.getStatus());
            return repo.save(existing);
        }

        return repo.save(attempt);
    }

    @GetMapping("/all")
    public List<Attempt> getAllAttempts() {
        return repo.findAll();
    }
}