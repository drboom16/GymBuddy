package com.gymbuddy.config;

import com.gymbuddy.model.Achievement;
import com.gymbuddy.model.Workout;
import com.gymbuddy.model.Exercises.*;
import com.gymbuddy.repository.AchievementRepository;
import com.gymbuddy.repository.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final AchievementRepository achievementRepository;

    public DataInitializer(WorkoutRepository workoutRepository, AchievementRepository achievementRepository) {
        this.workoutRepository = workoutRepository;
        this.achievementRepository = achievementRepository;
    }

    @Override
    public void run(String... args) {
        // Check if default Arms workout already exists
        List<Workout> existingWorkouts = workoutRepository.findAll();
        boolean armsWorkoutExists = existingWorkouts.stream()
            .anyMatch(w -> "Arms".equals(w.getWorkoutName()));

        if (!armsWorkoutExists) {
            // Create default Arms workout
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Arms("Bicep-curls", 3));
            exercises.add(new Arms("Shoulder press", 3));
            exercises.add(new Arms("Wrist curls", 3));
            exercises.add(new Arms("Dumbell shoulder flys", 3));
            exercises.add(new Arms("Tricep press-downs", 3));

            Workout armsWorkout = new Workout("Arms", exercises);
            workoutRepository.save(armsWorkout);
        }

        // Check if achievements already exist
        List<Achievement> existingAchievements = achievementRepository.findAll();
        
        if (existingAchievements.isEmpty()) {
            // Create default achievements
            List<Achievement> achievements = new ArrayList<>();
            
            achievements.add(new Achievement("First Workout", 1));
            achievements.add(new Achievement("5 Workouts Complete", 5));
            achievements.add(new Achievement("10 Workouts Complete", 10));
            achievements.add(new Achievement("25 Workouts Complete", 25));
            achievements.add(new Achievement("50 Workouts Complete", 50));
            achievements.add(new Achievement("100 Workouts Complete", 100));
            achievements.add(new Achievement("First Set Complete", 1));
            achievements.add(new Achievement("10 Sets Complete", 10));
            achievements.add(new Achievement("50 Sets Complete", 50));
            achievements.add(new Achievement("100 Sets Complete", 100));
            achievements.add(new Achievement("Week Warrior", 7));
            achievements.add(new Achievement("Monthly Champion", 30));
            
            achievementRepository.saveAll(achievements);
        }
    }
}

