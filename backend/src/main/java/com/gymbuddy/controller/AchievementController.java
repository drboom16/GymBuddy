package com.gymbuddy.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.gymbuddy.model.Achievement;

import java.util.*;

import com.gymbuddy.service.AchievementService;
import com.gymbuddy.model.ExerciseResponse;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {
    
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    /**
     * GET
     * /api/achievements
     */
    @GetMapping
    public ResponseEntity<List<com.gymbuddy.model.Achievement>> getAllAchievements() {
        try {
            List<Achievement> achievements = achievementService.getAllAchievements();
            return ResponseEntity.ok(achievements);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * PUT
     * /api/achievements/update
     */
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateAchievements() {
        try {
            int newCoinTotal = achievementService.updateAchievements();
            Map<String, Object> response = new HashMap<>();
            response.put("coins", newCoinTotal);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Achievements could not be updated: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * PUT
     * /api/achievements/exercise-response
     */
    @PutMapping("/exercise-response")
    public ResponseEntity<Map<String, Object>> createExerciseResponse(@RequestBody Map<String, Object> request) {
        try {
            String type = (String) request.get("type");
            
            int duration;
            Object durationObj = request.get("duration");
            if (durationObj instanceof Integer) {
                duration = (Integer) durationObj;
            } else if (durationObj instanceof String) {
                String durationStr = (String) durationObj;
                duration = Integer.parseInt(durationStr);
            } else {
                throw new IllegalArgumentException("Duration must be a number or numeric string");
            }
            
            ExerciseResponse exerciseResponse = achievementService.createExerciseResponse(type, duration);
            Map<String, Object> response = new HashMap<>();
            response.put("exerciseResponse", exerciseResponse);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to create exercise response: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
