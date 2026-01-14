package com.gymbuddy.service;

import com.gymbuddy.model.Workout;
import com.gymbuddy.model.Exercises.*;
import com.gymbuddy.repository.WorkoutRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class WorkoutService {
    
    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Workout colors (excluding index 0 which is reserved for default Arms workout)
    private static final String[] WORKOUT_COLORS = {
        "#f48952", // Orange/Coral (index 0 in this array, but index 1 in full array)
        "#8991f3", // Purple/Blue
        "#8cea64", // Green
        "#ff6b9d", // Pink
        "#4ecdc4", // Turquoise
        "#ffa07a", // Light Salmon
        "#ba68c8", // Purple
        "#ffd93d", // Bright Yellow
        "#6c5ce7"  // Indigo
    };

    public Workout createWorkout(String name, List<Exercise> exercises) {
        // Get all existing workouts (excluding the default "Arms" workout)
        List<Workout> allWorkouts = workoutRepository.findAllByOrderByIdAsc();
        List<Workout> nonArmsWorkouts = allWorkouts.stream()
            .filter(w -> !"Arms".equals(w.getWorkoutName()))
            .toList();
        
        // Collect all currently used colors
        Set<String> usedColors = new HashSet<>();
        for (Workout workout : nonArmsWorkouts) {
            if (workout.getColor() != null) {
                usedColors.add(workout.getColor());
            }
        }
        
        // Find the first unused color from the array
        String assignedColor = null;
        for (String color : WORKOUT_COLORS) {
            if (!usedColors.contains(color)) {
                assignedColor = color;
                break;
            }
        }
        
        // If all colors are used, cycle back to the first color
        if (assignedColor == null) {
            int colorIndex = (int) (nonArmsWorkouts.size() % WORKOUT_COLORS.length);
            assignedColor = WORKOUT_COLORS[colorIndex];
        }
        
        Workout workout = new Workout(name, exercises, assignedColor);
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

    /**
     * Mark a workout as completed by setting the completedAt timestamp
     */
    public Workout markWorkoutAsCompleted(Long id) {
        Workout workout = workoutRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Workout not found"));
        
        workout.setCompletedAt(LocalDateTime.now());
        return workoutRepository.save(workout);
    }

    /**
     * Get the last 3 completed workouts, ordered by completion date descending
     */
    public List<Workout> getLast3CompletedWorkouts() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        return workoutRepository.findLast3CompletedWorkouts(pageRequest);
    }

    /**
     * Mark a specific set as completed for an exercise in a workout
     */
    public Workout markSetAsCompleted(Long workoutId, int exerciseIndex, int setIndex) {
        Workout workout = workoutRepository.findById(workoutId)
            .orElseThrow(() -> new RuntimeException("Workout not found"));
        
        if (exerciseIndex < 0 || exerciseIndex >= workout.getExercises().size()) {
            throw new RuntimeException("Exercise index out of bounds");
        }
        
        Exercise exercise = workout.getExercises().get(exerciseIndex);
        exercise.markSetComplete(setIndex);
        
        // Save workout - cascade will save the exercise and its sets
        return workoutRepository.save(workout);
    }

    /**
     * Reset all set completions for a workout
     */
    public Workout resetWorkoutSets(Long workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
            .orElseThrow(() -> new RuntimeException("Workout not found"));
        
        // Reset all set completions for all exercises by setting sets to all false
        for (Exercise exercise : workout.getExercises()) {
            int noSets = exercise.getNoSets();
            List<com.gymbuddy.model.ExerciseSet> sets = new ArrayList<>();
            for (int i = 0; i < noSets; i++) {
                sets.add(new com.gymbuddy.model.ExerciseSet(false));
            }
            exercise.setSets(sets);
        }
        
        return workoutRepository.save(workout);
    }
}
