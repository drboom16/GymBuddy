package com.gymbuddy.service;

import com.gymbuddy.model.Achievement;
import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.repository.AchievementRepository;
import com.gymbuddy.repository.GymBuddyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymBuddyService {
    
    private final GymBuddyRepository gymBuddyRepository;
    private final AchievementRepository achievementRepository;

    public GymBuddyService(GymBuddyRepository gymBuddyRepository, AchievementRepository achievementRepository) {
        this.gymBuddyRepository = gymBuddyRepository;
        this.achievementRepository = achievementRepository;
    }

    public List<GymBuddy> getAllGymBuddies() {
        return gymBuddyRepository.findAll();
    }

    public GymBuddy getGymBuddyById(Long id) {
        return gymBuddyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("GymBuddy not found with id: " + id));
    }

    public boolean areAllAchievementsComplete() {
        List<Achievement> allAchievements = achievementRepository.findAll();
        if (allAchievements.isEmpty()) {
            return false;
        }
        // Only check lifetime achievements (isDaily = false)
        return allAchievements.stream()
            .filter(achievement -> !achievement.isDaily())
            .allMatch(achievement -> achievement.getCurrent() >= achievement.getTarget());
    }

    public boolean areAllDailyAchievementsComplete() {
        List<Achievement> allAchievements = achievementRepository.findAll();
        if (allAchievements.isEmpty()) {
            return false;
        }
        // Only check daily achievements (isDaily = true)
        return allAchievements.stream()
            .filter(Achievement::isDaily)
            .allMatch(achievement -> achievement.getCurrent() >= achievement.getTarget());
    }
}
