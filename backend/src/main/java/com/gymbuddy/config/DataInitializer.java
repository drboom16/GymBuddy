package com.gymbuddy.config;

import com.gymbuddy.model.Achievement;
import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.model.Workout;
import com.gymbuddy.model.Exercises.*;
import com.gymbuddy.repository.AchievementRepository;
import com.gymbuddy.repository.GymBuddyRepository;
import com.gymbuddy.repository.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WorkoutRepository workoutRepository;
    private final AchievementRepository achievementRepository;
    private final GymBuddyRepository gymBuddyRepository;

    public DataInitializer(WorkoutRepository workoutRepository, AchievementRepository achievementRepository, GymBuddyRepository gymBuddyRepository) {
        this.workoutRepository = workoutRepository;
        this.achievementRepository = achievementRepository;
        this.gymBuddyRepository = gymBuddyRepository;
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
            // Create default achievements with coin rewards
            List<Achievement> achievements = new ArrayList<>();
            
            achievements.add(new Achievement("First Workout", 1, 10));
            achievements.add(new Achievement("5 Workouts Complete", 5, 25));
            achievements.add(new Achievement("10 Workouts Complete", 10, 50));
            achievements.add(new Achievement("25 Workouts Complete", 25, 100));
            achievements.add(new Achievement("50 Workouts Complete", 50, 200));
            achievements.add(new Achievement("100 Workouts Complete", 100, 500));
            achievements.add(new Achievement("First Set Complete", 1, 5));
            achievements.add(new Achievement("10 Sets Complete", 10, 30));
            achievements.add(new Achievement("50 Sets Complete", 50, 150));
            achievements.add(new Achievement("100 Sets Complete", 100, 300));
            achievements.add(new Achievement("Week Warrior", 7, 75));
            achievements.add(new Achievement("Monthly Champion", 30, 250));
            
            achievementRepository.saveAll(achievements);
        }

        // Check if gymbuddies already exist
        List<GymBuddy> existingGymBuddies = gymBuddyRepository.findAll();
        
        if (existingGymBuddies.isEmpty()) {
            // Create default gymbuddies
            List<GymBuddy> gymBuddies = new ArrayList<>();
            
            gymBuddies.add(new GymBuddy("Rookie", 0, "/public/gymbuddy-rookie.png"));
            gymBuddies.add(new GymBuddy("Warrior", 100, "/public/gymbuddy-warrior.png"));
            gymBuddies.add(new GymBuddy("Champion", 250, "/public/gymbuddy-champion.png"));
            gymBuddies.add(new GymBuddy("Elite", 500, "/public/gymbuddy-elite.png"));
            gymBuddies.add(new GymBuddy("Legend", 1000, "/public/gymbuddy-legend.png"));
            gymBuddies.add(new GymBuddy("Mythic", 2500, "/public/gymbuddy-mythic.png"));
            
            gymBuddyRepository.saveAll(gymBuddies);
        }
    }
}

