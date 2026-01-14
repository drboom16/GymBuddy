package com.gymbuddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.gymbuddy.model.Workout;
import com.gymbuddy.model.Exercises.Exercise;
import com.gymbuddy.service.WorkoutService;
import java.util.*;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    
    @Autowired
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    /**
     * POST
     * /api/workouts
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
                    exercises.add(workoutService.createExercise(name, noSets, type));
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
                    exercises.add(workoutService.createExercise(name, noSets, type));
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

    /**
     * GET
     * /api/workouts/{workoutId}
     */
    @GetMapping("/{workoutId}")
    public ResponseEntity<Workout> getWorkout(@PathVariable Long workoutId) {
        try {
            Workout workout = workoutService.getWorkout(workoutId);
            return ResponseEntity.ok(workout);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * PATCH
     * /api/workouts/{workoutId}/complete
     * Mark a workout as completed
     */
    @PutMapping("/{workoutId}/complete")
    public ResponseEntity<Map<String, Object>> markWorkoutAsCompleted(@PathVariable Long workoutId) {
        try {
            Workout workout = workoutService.markWorkoutAsCompleted(workoutId);
            Map<String, Object> response = new HashMap<>();
            response.put("id", workout.getId());
            response.put("workoutName", workout.getWorkoutName());
            response.put("completedAt", workout.getCompletedAt());
            response.put("message", "Workout marked as completed");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to mark workout as completed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * GET
     * /api/workouts/completed/recent
     * Get the last 3 completed workouts
     */
    @GetMapping("/completed/recent")
    public ResponseEntity<List<Workout>> getLast3CompletedWorkouts() {
        try {
            List<Workout> workouts = workoutService.getLast3CompletedWorkouts();
            return ResponseEntity.ok(workouts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * PATCH
     * /api/workouts/{workoutId}/exercises/{exerciseIndex}/sets/{setIndex}/complete
     * Mark a specific set as completed
     */
    @PatchMapping("/{workoutId}/exercises/{exerciseIndex}/sets/{setIndex}/complete")
    public ResponseEntity<Map<String, Object>> markSetAsCompleted(
            @PathVariable Long workoutId,
            @PathVariable int exerciseIndex,
            @PathVariable int setIndex) {
        try {
            Workout workout = workoutService.markSetAsCompleted(workoutId, exerciseIndex, setIndex);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Set marked as completed");
            response.put("workoutId", workout.getId());
            response.put("exerciseIndex", exerciseIndex);
            response.put("setIndex", setIndex);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to mark set as completed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * DELETE
     * /api/workouts/{workoutId}/reset
     * Reset all set completions for a workout
     */
    @DeleteMapping("/{workoutId}/reset")
    public ResponseEntity<Map<String, Object>> resetWorkoutSets(@PathVariable Long workoutId) {
        try {
            Workout workout = workoutService.resetWorkoutSets(workoutId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Workout sets reset successfully");
            response.put("workoutId", workout.getId());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to reset workout sets: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
