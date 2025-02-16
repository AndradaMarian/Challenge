package com.coding.Challenge.controller;

import com.coding.Challenge.model.User;
import com.coding.Challenge.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public Map<String, String> signUp(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        String message = authService.registerUser(name, email, password);
        return Map.of("message", message);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String email, @RequestParam String password) {
        String token = authService.loginUser(email, password);
        return Map.of("token", token);
    }

    @GetMapping("/user")
    public Map<String, String> getUser(@RequestHeader("Authorization") String token) {
        User user = authService.getUserDetails(token);
        if (user == null) {
            return Map.of("error", "Invalid token");
        }
        return Map.of("name", user.getName(), "email", user.getEmail());
    }
}