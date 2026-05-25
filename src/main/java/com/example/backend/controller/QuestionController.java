package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.Question;
import com.example.backend.repository.QuestionRepository;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionRepository repo;

    @GetMapping
    public List<Question> getQuestions() {
        return repo.findAll();
    }

    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return repo.save(question);
    }
}