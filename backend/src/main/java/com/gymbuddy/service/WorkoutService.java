package com.gymbuddy.service;

import com.gymbuddy.model.Exercise;
import com.gymbuddy.model.Workout;
import com.gymbuddy.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WorkoutService {
    
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public Workout createWorkout(String name, List<Exercise> exercises) {
        Workout workout = new Workout(name, exercises);
        return workoutRepository.save(workout);
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
}
