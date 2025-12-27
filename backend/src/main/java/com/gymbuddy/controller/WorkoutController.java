package com.gymbuddy.controller;

import com.gymbuddy.model.Workout;
import com.gymbuddy.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin(origins = "*")
public class WorkoutController {
    
    private final WorkoutService workoutService;
    
    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }
    
    /**
     * POST /api/workouts
     * Save a completed workout
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveWorkout(@RequestBody Map<String, String> request) {
        try {
            String workoutType = request.get("workoutType");
            String intensity = request.get("intensity");
            String duration = request.get("duration");
            
            if (workoutType == null || intensity == null || duration == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Workout type, intensity, and duration are required");
                return ResponseEntity.badRequest().body(error);
            }
            
            Workout workout = workoutService.saveWorkout(workoutType, intensity, duration);
            
            Map<String, Object> response = new HashMap<>();
            response.put("id", workout.getId());
            response.put("workoutType", workout.getWorkoutType());
            response.put("intensity", workout.getIntensity());
            response.put("duration", workout.getDuration());
            response.put("completedAt", workout.getCompletedAt());
            response.put("message", "Workout saved successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to save workout: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    /**
     * GET /api/workouts
     * Get all workouts ordered by completion date (newest first)
     */
    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        try {
            List<Workout> workouts = workoutService.getAllWorkouts();
            return ResponseEntity.ok(workouts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

