package com.gymbuddy.service;

import com.gymbuddy.model.Workout;
import com.gymbuddy.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutService {
    
    private final WorkoutRepository workoutRepository;
    
    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }
    
    /**
     * Save a completed workout
     */
    public Workout saveWorkout(String workoutType, String intensity, String duration) {
        // Parse duration to seconds for easier querying
        Integer durationSeconds = parseDurationToSeconds(duration);
        
        Workout workout = new Workout(
            workoutType.toUpperCase(),
            intensity.toUpperCase(),
            duration,
            LocalDateTime.now(),
            durationSeconds
        );
        
        return workoutRepository.save(workout);
    }
    
    /**
     * Get all workouts ordered by completion date (newest first)
     */
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAllByOrderByCompletedAtDesc();
    }
    
    /**
     * Parse duration string (e.g., "30s", "1m", "2m", "3m") to seconds
     */
    private Integer parseDurationToSeconds(String duration) {
        if (duration == null || duration.isEmpty()) {
            return 0;
        }
        
        duration = duration.trim().toLowerCase();
        
        if (duration.endsWith("s")) {
            return Integer.parseInt(duration.substring(0, duration.length() - 1));
        } else if (duration.endsWith("m")) {
            return Integer.parseInt(duration.substring(0, duration.length() - 1)) * 60;
        }
        
        return 0;
    }
}

