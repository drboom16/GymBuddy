package com.gymbuddy.config;

import com.gymbuddy.model.Achievement;
import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.model.User;
import com.gymbuddy.model.UserGymBuddy;
import com.gymbuddy.model.Workout;
import com.gymbuddy.model.Exercises.*;
import com.gymbuddy.repository.AchievementRepository;
import com.gymbuddy.repository.GymBuddyRepository;
import com.gymbuddy.repository.UserGymBuddyRepository;
import com.gymbuddy.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final UserGymBuddyRepository userGymBuddyRepository;

    public DataInitializer(WorkoutRepository workoutRepository, AchievementRepository achievementRepository, GymBuddyRepository gymBuddyRepository, UserRepository userRepository, UserGymBuddyRepository userGymBuddyRepository) {
        this.workoutRepository = workoutRepository;
        this.achievementRepository = achievementRepository;
        this.gymBuddyRepository = gymBuddyRepository;
        this.userRepository = userRepository;
        this.userGymBuddyRepository = userGymBuddyRepository;
    }

    @Override
    public void run(String... args) {
        // Check if default Arms workout already exists
        List<Workout> existingWorkouts = workoutRepository.findAll();
        boolean armsWorkoutExists = existingWorkouts.stream()
            .anyMatch(w -> "Arms".equals(w.getWorkoutName()));

        if (!armsWorkoutExists) {
            // Create default Arms workout with the first color (#ffbe1e)
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Arms("Bicep-curls", 3));
            exercises.add(new Arms("Shoulder press", 3));
            exercises.add(new Arms("Wrist curls", 3));
            exercises.add(new Arms("Dumbell shoulder flys", 3));
            exercises.add(new Arms("Tricep press-downs", 3));

            Workout armsWorkout = new Workout("Arms", exercises, "#ffbe1e");
            workoutRepository.save(armsWorkout);
        }

        // Check if achievements already exist
        List<Achievement> existingAchievements = achievementRepository.findAll();
        
        if (existingAchievements.isEmpty()) {
            // Create daily achievements (3 same ones that can be completed in one workout session)
            // Total daily rewards: 45 coins per day. Over ~112 days, this provides 5000+ coins for Second Slot
            List<Achievement> dailyAchievements = new ArrayList<>();
            dailyAchievements.add(new Achievement("Complete 1 Workout", 1, 15, true));
            dailyAchievements.add(new Achievement("Complete 5 Sets", 5, 10, true));
            dailyAchievements.add(new Achievement("Workout for 2 Minutes", 120, 20, true)); // 120 seconds = 2 minutes
            
            // Create lifetime achievements with coin rewards
            // Total rewards: 15,500+ coins (sufficient for all purchasable gymbuddies: 600+3000+2400+2800+1200+5000 = 15,000)
            List<Achievement> lifetimeAchievements = new ArrayList<>();
            lifetimeAchievements.add(new Achievement("First Workout", 1, 100, false));
            lifetimeAchievements.add(new Achievement("5 Workouts Complete", 5, 300, false));
            lifetimeAchievements.add(new Achievement("10 Workouts Complete", 10, 500, false));
            lifetimeAchievements.add(new Achievement("25 Workouts Complete", 25, 1000, false));
            lifetimeAchievements.add(new Achievement("50 Workouts Complete", 50, 2000, false));
            lifetimeAchievements.add(new Achievement("100 Workouts Complete", 100, 3500, false));
            lifetimeAchievements.add(new Achievement("First Set Complete", 1, 50, false));
            lifetimeAchievements.add(new Achievement("10 Sets Complete", 10, 200, false));
            lifetimeAchievements.add(new Achievement("50 Sets Complete", 50, 800, false));
            lifetimeAchievements.add(new Achievement("100 Sets Complete", 100, 1500, false));
            lifetimeAchievements.add(new Achievement("Week Warrior", 7, 500, false));
            lifetimeAchievements.add(new Achievement("Monthly Champion", 30, 1000, false));
            lifetimeAchievements.add(new Achievement("200 Workouts Complete", 200, 2000, false));
            lifetimeAchievements.add(new Achievement("500 Sets Complete", 500, 1500, false));
            
            // Body part specific achievements (arms, chest, legs, back)
            // Arms achievements
            lifetimeAchievements.add(new Achievement("First Arms Exercise", 1, 50, "arms", null));
            lifetimeAchievements.add(new Achievement("5 Arms Exercises", 5, 200, "arms", null));
            lifetimeAchievements.add(new Achievement("10 Arms Exercises", 10, 400, "arms", null));
            lifetimeAchievements.add(new Achievement("25 Arms Exercises", 25, 800, "arms", null));
            lifetimeAchievements.add(new Achievement("50 Arms Exercises", 50, 1500, "arms", null));
            
            // Chest achievements
            lifetimeAchievements.add(new Achievement("First Chest Exercise", 1, 50, "chest", null));
            lifetimeAchievements.add(new Achievement("5 Chest Exercises", 5, 200, "chest", null));
            lifetimeAchievements.add(new Achievement("10 Chest Exercises", 10, 400, "chest", null));
            lifetimeAchievements.add(new Achievement("25 Chest Exercises", 25, 800, "chest", null));
            lifetimeAchievements.add(new Achievement("50 Chest Exercises", 50, 1500, "chest", null));
            
            // Legs achievements
            lifetimeAchievements.add(new Achievement("First Legs Exercise", 1, 50, "legs", null));
            lifetimeAchievements.add(new Achievement("5 Legs Exercises", 5, 200, "legs", null));
            lifetimeAchievements.add(new Achievement("10 Legs Exercises", 10, 400, "legs", null));
            lifetimeAchievements.add(new Achievement("25 Legs Exercises", 25, 800, "legs", null));
            lifetimeAchievements.add(new Achievement("50 Legs Exercises", 50, 1500, "legs", null));
            
            // Back achievements
            lifetimeAchievements.add(new Achievement("First Back Exercise", 1, 50, "back", null));
            lifetimeAchievements.add(new Achievement("5 Back Exercises", 5, 200, "back", null));
            lifetimeAchievements.add(new Achievement("10 Back Exercises", 10, 400, "back", null));
            lifetimeAchievements.add(new Achievement("25 Back Exercises", 25, 800, "back", null));
            lifetimeAchievements.add(new Achievement("50 Back Exercises", 50, 1500, "back", null));
            
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
            
            // Default white GymBuddy (free, always available)
            gymBuddies.add(new GymBuddy("GymBuddy", 0, "/public/shop/gymbuddy.png", false));
            gymBuddies.add(new GymBuddy("EarthBuddy", 600, "/public/shop/gymbuddy-rookie.png", false));
            gymBuddies.add(new GymBuddy("MysticalBuddy", 3000, "/public/shop/gymbuddy-warrior.png", false));
            gymBuddies.add(new GymBuddy("LegendaryBuddy", 0, "/public/shop/gymbuddy-champion.png", true));
            gymBuddies.add(new GymBuddy("DarkBuddy", 2400, "/public/shop/gymbuddy-elite.png", false));
            gymBuddies.add(new GymBuddy("AncientBuddy", 2800, "/public/shop/gymbuddy-legend.png", false));
            gymBuddies.add(new GymBuddy("PinkBuddy", 1200, "/public/shop/gymbuddy-mythic.png", false));
            gymBuddies.add(new GymBuddy("Second Slot", 5000, "/public/shop/second-slot.png", false, false));
            
            gymBuddyRepository.saveAll(gymBuddies);
        }

        // Initialize default user if it doesn't exist
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            User defaultUser = new User(600);
            userRepository.save(defaultUser);
            
            // Give default user the white GymBuddy as active
            GymBuddy defaultGymBuddy = gymBuddyRepository.findAll().stream()
                .filter(gb -> "GymBuddy".equals(gb.getName()))
                .findFirst()
                .orElse(null);
            
            if (defaultGymBuddy != null) {
                UserGymBuddy userGymBuddy = new UserGymBuddy(defaultUser, defaultGymBuddy, true);
                userGymBuddyRepository.save(userGymBuddy);
            }
        } else {
            // Ensure existing users have the default GymBuddy if they don't have any
            User user = allUsers.get(0);
            List<UserGymBuddy> userGymBuddies = userGymBuddyRepository.findByUser(user);
            if (userGymBuddies.isEmpty()) {
                GymBuddy defaultGymBuddy = gymBuddyRepository.findAll().stream()
                    .filter(gb -> "GymBuddy".equals(gb.getName()))
                    .findFirst()
                    .orElse(null);
                
                if (defaultGymBuddy != null) {
                    UserGymBuddy userGymBuddy = new UserGymBuddy(user, defaultGymBuddy, true);
                    userGymBuddyRepository.save(userGymBuddy);
                }
            }
        }
    }
}

