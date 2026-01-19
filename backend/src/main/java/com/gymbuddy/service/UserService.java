package com.gymbuddy.service;

import com.gymbuddy.model.User;
import com.gymbuddy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser() {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            User newUser = new User(600);
            return userRepository.save(newUser);
        } else {
            // Return the first user (assuming single user system)
            return allUsers.get(0);
        }
    }

    public int getCoins() {
        User user = getOrCreateUser();
        return user.getCoins();
    }

    public void addCoins(int amount) {
        User user = getOrCreateUser();
        user.addCoins(amount);
        userRepository.save(user);
    }

    public boolean deductCoins(int amount) {
        User user = getOrCreateUser();
        if (user.getCoins() >= amount) {
            user.setCoins(user.getCoins() - amount);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public User getUser() {
        return getOrCreateUser();
    }
}
