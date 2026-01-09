package com.gymbuddy.service;

import com.gymbuddy.model.Workout;
import com.gymbuddy.model.Exercises.*;
import com.gymbuddy.repository.WorkoutRepository;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WorkoutService {
    
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout createWorkout(String name, List<Exercise> exercises) {
        Workout workout = new Workout(name, exercises);
        return workoutRepository.save(workout);
    }

    public Exercise createExercise(String name, Integer noSets, String type) {
        switch (type) {
            case "chest":
                return new Chest(name, noSets);
            case "back":
                return new Back(name, noSets);
            case "arms":
                return new Arms(name, noSets);
            case "legs":
                return new Legs(name, noSets);
            default:
                throw new IllegalArgumentException("Invalid exercise type");
        }
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAllByOrderByIdAsc();
    }

    /**
     * Update workout by ID
     */
    public Workout updateWorkout(Long id, String name, List<Exercise> exercises) {
        Workout workout = workoutRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Workout not found"));
        
        workout.setWorkoutName(name);
        workout.setExercises(new ArrayList<>(exercises));
        
        return workoutRepository.save(workout);
    }

    /**
     * Delete a workout by ID
     */
    public void deleteWorkout(Long id) {
        if (!workoutRepository.existsById(id)) {
            throw new RuntimeException("Workout not found");
        }
        workoutRepository.deleteById(id);
    }

    /**
     * Get a workout by ID
     */
    public Workout getWorkout(Long id) {
        return workoutRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Workout not found"));
    }
}
