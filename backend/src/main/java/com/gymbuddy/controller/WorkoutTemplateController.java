package com.gymbuddy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.gymbuddy.model.Workout;
import com.gymbuddy.model.Exercise;
import com.gymbuddy.service.WorkoutService;
import java.util.*;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutTemplateController {
    
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutTemplateController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * POST
     * /api/workoutTemplates
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> saveWorkoutTemplate(@RequestBody Map<String, Object> request) {
        try {
            String workoutName = (String) request.get("workoutName");
            
            // Parse exercises from JSON
            List<Map<String, Object>> exercisesData = (List<Map<String, Object>>) request.get("exercises");
            List<Exercise> exercises = new ArrayList<>();
            
            if (exercisesData != null) {
                for (Map<String, Object> exerciseData : exercisesData) {
                    String name = (String) exerciseData.get("name");
                    // Default to 0 sets if not provided
                    Integer noSets = exerciseData.get("noSets") != null 
                        ? ((Number) exerciseData.get("noSets")).intValue() 
                        : 0;
                    String type = (String) exerciseData.get("type");
                    exercises.add(new Exercise(name, noSets, type));
                }
            }
            
            Workout workout = workoutService.createWorkout(workoutName, exercises);
    
            Map<String, Object> response = new HashMap<>();
            response.put("id", workout.getId());
            response.put("workoutName", workout.getWorkoutName());
            response.put("message", "Workout saved successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to save workout: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * GET
     * /api/workouts
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

    /**
     * DELETE
     * /api/workouts/{workoutId}
     */
    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Map<String, Object>> deleteWorkout(@PathVariable Long workoutId) {
        try {
            workoutService.deleteWorkout(workoutId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Workout deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to delete workout: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * PUT
     * /api/workouts/{workoutId}
     */
    @PutMapping("/{workoutId}")
    public ResponseEntity<Map<String, Object>> updateWorkout(@PathVariable Long workoutId, @RequestBody Map<String, Object> request) {
        try {
            String workoutName = (String) request.get("workoutName");

            // Parse exercises from JSON
            List<Map<String, Object>> exercisesData = (List<Map<String, Object>>) request.get("exercises");
            List<Exercise> exercises = new ArrayList<>();
            
            if (exercisesData != null) {
                for (Map<String, Object> exerciseData : exercisesData) {
                    String name = (String) exerciseData.get("name");
                    // Default to 0 sets if not provided
                    Integer noSets = exerciseData.get("noSets") != null 
                        ? ((Number) exerciseData.get("noSets")).intValue() 
                        : 0;
                    String type = (String) exerciseData.get("type");
                    exercises.add(new Exercise(name, noSets, type));
                }
            }
            
            Workout workout = workoutService.updateWorkout(workoutId, workoutName, exercises);
    
            Map<String, Object> response = new HashMap<>();
            response.put("id", workout.getId());
            response.put("workoutName", workout.getWorkoutName());
            response.put("message", "Workout saved successfully");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to update workout: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

}
