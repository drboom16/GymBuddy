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

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public ExerciseResponse createExerciseResponse(String type, String intensity, int duration) {
        ExerciseResponse exerciseResponse = new ExerciseResponse(type, intensity, duration);
        exerciseResponses.add(exerciseResponse);
        return exerciseResponse;
    }

    public void updateAchievements() {
        if (exerciseResponses.isEmpty()) {
            return;
        }

        List<Achievement> allAchievements = achievementRepository.findAll();
        Set<Achievement> achievementsToUpdate = new HashSet<>();

        for (ExerciseResponse response : exerciseResponses) {
            for (Achievement achievement : allAchievements) {
                // Only update if achievement hasn't reached target and matches all tags
                if (achievement.getCurrent() < achievement.getTarget() && 
                    matchesAllTags(achievement, response)) {
                    
                    achievement.incrementCurrent();
                    achievementsToUpdate.add(achievement);
                }
            }
        }

        // Batch save all updated achievements
        if (!achievementsToUpdate.isEmpty()) {
            achievementRepository.saveAll(achievementsToUpdate);
        }

        // Clear processed responses
        exerciseResponses.clear();
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

        // Check intensity tag
        if (achievement.getIntensity() != null && 
            !achievement.getIntensity().equalsIgnoreCase(response.getIntensity())) {
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
