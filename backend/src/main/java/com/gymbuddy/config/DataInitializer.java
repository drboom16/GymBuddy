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
            // Create daily achievements (3 same ones that can be completed in one workout session)
            List<Achievement> dailyAchievements = new ArrayList<>();
            dailyAchievements.add(new Achievement("Complete 1 Workout", 1, 15, true));
            dailyAchievements.add(new Achievement("Complete 5 Sets", 5, 10, true));
            dailyAchievements.add(new Achievement("Workout for 2 Minutes", 120, 20, true)); // 120 seconds = 2 minutes
            
            // Create lifetime achievements with coin rewards
            List<Achievement> lifetimeAchievements = new ArrayList<>();
            lifetimeAchievements.add(new Achievement("First Workout", 1, 10, false));
            lifetimeAchievements.add(new Achievement("5 Workouts Complete", 5, 25, false));
            lifetimeAchievements.add(new Achievement("10 Workouts Complete", 10, 50, false));
            lifetimeAchievements.add(new Achievement("25 Workouts Complete", 25, 100, false));
            lifetimeAchievements.add(new Achievement("50 Workouts Complete", 50, 200, false));
            lifetimeAchievements.add(new Achievement("100 Workouts Complete", 100, 500, false));
            lifetimeAchievements.add(new Achievement("First Set Complete", 1, 5, false));
            lifetimeAchievements.add(new Achievement("10 Sets Complete", 10, 30, false));
            lifetimeAchievements.add(new Achievement("50 Sets Complete", 50, 150, false));
            lifetimeAchievements.add(new Achievement("100 Sets Complete", 100, 300, false));
            lifetimeAchievements.add(new Achievement("Week Warrior", 7, 75, false));
            lifetimeAchievements.add(new Achievement("Monthly Champion", 30, 250, false));
            
            // Save all achievements
            List<Achievement> allAchievements = new ArrayList<>();
            allAchievements.addAll(dailyAchievements);
            allAchievements.addAll(lifetimeAchievements);
            achievementRepository.saveAll(allAchievements);
        }

        // Check if gymbuddies already exist
        List<GymBuddy> existingGymBuddies = gymBuddyRepository.findAll();
        
        if (existingGymBuddies.isEmpty()) {
            // Create default gymbuddies
            List<GymBuddy> gymBuddies = new ArrayList<>();
            
            gymBuddies.add(new GymBuddy("EarthBuddy", 0, "/public/gymbuddy-rookie.png", false));
            gymBuddies.add(new GymBuddy("MysticalBuddy", 100, "/public/gymbuddy-warrior.png", false));
            gymBuddies.add(new GymBuddy("LegendaryBuddy", 0, "/public/gymbuddy-champion.png", true));
            gymBuddies.add(new GymBuddy("DarkBuddy", 500, "/public/gymbuddy-elite.png", false));
            gymBuddies.add(new GymBuddy("AncientBuddy", 1000, "/public/gymbuddy-legend.png", false));
            gymBuddies.add(new GymBuddy("PinkBuddy", 2500, "/public/gymbuddy-mythic.png", false));
            
            gymBuddyRepository.saveAll(gymBuddies);
        }
    }
}

