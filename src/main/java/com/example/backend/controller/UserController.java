package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository repo;

    // REGISTER USER
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setName(user.getName().trim());
        user.setEmail(user.getEmail().trim());
        user.setPassword(user.getPassword().trim());

        return repo.save(user);
    }

    // LOGIN USER
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        String email = user.getEmail().trim();
        String password = user.getPassword().trim();

        User existing = repo.findByEmail(email);

        if (existing != null &&
            existing.getPassword().trim().equals(password)) {

            return "Login Success";
        }

        return "Invalid Credentials";
    }

    // TEST API
    @GetMapping("/test")
    public String test() {
        return "Backend Working";
    }
}