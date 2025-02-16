package com.coding.Challenge.service;


import com.coding.Challenge.data.UserRepository;
import com.coding.Challenge.input.KafkaProducer;
import com.coding.Challenge.model.User;
import com.coding.Challenge.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final KafkaProducer kafkaProducer;

    public AuthService(UserRepository userRepository, TokenService tokenService, KafkaProducer kafkaProducer) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.kafkaProducer = kafkaProducer;
    }

    public String registerUser(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "User already exists!";
        }
        User user = new User(name, email, password);
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return tokenService.generateToken(email);
        }
        return "Invalid credentials!";
    }

    public User getUserDetails(String token) {
        String email = tokenService.validateToken(token);
        return userRepository.findByEmail(email).orElse(null);
    }
}