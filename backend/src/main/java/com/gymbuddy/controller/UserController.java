package com.gymbuddy.controller;

import com.gymbuddy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/coins")
    public ResponseEntity<Map<String, Object>> getCoins() {
        int coins = userService.getCoins();
        Map<String, Object> response = new HashMap<>();
        response.put("coins", coins);
        return ResponseEntity.ok(response);
    }
}
