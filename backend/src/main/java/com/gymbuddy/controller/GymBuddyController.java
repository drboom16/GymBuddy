package com.gymbuddy.controller;

import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.service.GymBuddyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gymbuddies")
public class GymBuddyController {
    
    private final GymBuddyService gymBuddyService;

    public GymBuddyController(GymBuddyService gymBuddyService) {
        this.gymBuddyService = gymBuddyService;
    }

    @GetMapping
    public ResponseEntity<List<GymBuddy>> getAllGymBuddies() {
        return ResponseEntity.ok(gymBuddyService.getAllGymBuddies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymBuddy> getGymBuddyById(@PathVariable Long id) {
        return ResponseEntity.ok(gymBuddyService.getGymBuddyById(id));
    }
}
