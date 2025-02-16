package com.coding.Challenge.data;


import com.coding.Challenge.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }
}