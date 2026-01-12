package com.gymbuddy.service;

import com.gymbuddy.model.Achievement;
import com.gymbuddy.model.ExerciseResponse;
import com.gymbuddy.repository.AchievementRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AchievementService {

    // Cached exercise responses to update achievements upon workout completion
    private List<ExerciseResponse> exerciseResponses = new ArrayList<>();

    private final AchievementRepository achievementRepository;
    private final UserService userService;

    public AchievementService(AchievementRepository achievementRepository, UserService userService) {
        this.achievementRepository = achievementRepository;
        this.userService = userService;
    }

    public ExerciseResponse createExerciseResponse(String type, int duration) {
        ExerciseResponse exerciseResponse = new ExerciseResponse(type, duration);
        exerciseResponses.add(exerciseResponse);
        return exerciseResponse;
    }

    public int updateAchievements() {
        if (exerciseResponses.isEmpty()) {
            return userService.getCoins();
        }

        List<Achievement> allAchievements = achievementRepository.findAll();
        Set<Achievement> achievementsToUpdate = new HashSet<>();
        int totalCoinsAwarded = 0;

        for (ExerciseResponse response : exerciseResponses) {
            for (Achievement achievement : allAchievements) {
                // Only update if achievement hasn't reached target and matches all tags
                if (achievement.getCurrent() < achievement.getTarget() && 
                    matchesAllTags(achievement, response)) {
                    
                    boolean wasNotCompleted = achievement.getCurrent() < achievement.getTarget();
                    achievement.incrementCurrent();
                    achievementsToUpdate.add(achievement);
                    
                    // Award coins if achievement was just completed (reached target)
                    if (wasNotCompleted && achievement.getCurrent() >= achievement.getTarget()) {
                        totalCoinsAwarded += achievement.getCoinReward();
                    }
                }
            }
        }

        // Award coins to user
        if (totalCoinsAwarded > 0) {
            userService.addCoins(totalCoinsAwarded);
        }

        // Batch save all updated achievements
        if (!achievementsToUpdate.isEmpty()) {
            achievementRepository.saveAll(achievementsToUpdate);
        }

        // Clear processed responses
        exerciseResponses.clear();
        
        // Return updated coin total
        return userService.getCoins();
    }

    /**
     * Checks if an ExerciseResponse matches ALL tags of an Achievement.
     * A tag matches if:
     * - The achievement doesn't have that tag (null), OR
     * - The achievement's tag value matches the response's value
     */
    private boolean matchesAllTags(Achievement achievement, ExerciseResponse response) {
        // Check exercise type tag
        if (achievement.getExerciseType() != null && 
            !achievement.getExerciseType().equalsIgnoreCase(response.getType())) {
            return false;
        }

        // Check duration tag
        if (achievement.getDuration() != null && 
            !achievement.getDuration().equals(response.getDuration())) {
            return false;
        }

        // All tags match (or are null)
        return true;
    }

    public List<ExerciseResponse> getExerciseResponses() {
        return exerciseResponses;
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }
}
