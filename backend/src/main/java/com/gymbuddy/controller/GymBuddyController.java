package com.gymbuddy.controller;

import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.service.GymBuddyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/gymbuddies")
public class GymBuddyController {
    
    private final GymBuddyService gymBuddyService;

    public GymBuddyController(GymBuddyService gymBuddyService) {
        this.gymBuddyService = gymBuddyService;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllGymBuddies() {
        List<GymBuddy> gymBuddies = gymBuddyService.getAllGymBuddies();
        boolean allAchievementsComplete = gymBuddyService.areAllAchievementsComplete();
        boolean allDailyAchievementsComplete = gymBuddyService.areAllDailyAchievementsComplete();
        
        List<Map<String, Object>> gymBuddiesWithAvailability = gymBuddies.stream()
            .map(gymBuddy -> {
                Map<String, Object> gymBuddyMap = new HashMap<>();
                gymBuddyMap.put("id", gymBuddy.getId());
                gymBuddyMap.put("name", gymBuddy.getName());
                gymBuddyMap.put("coinCost", gymBuddy.getCoinCost());
                gymBuddyMap.put("imagePath", gymBuddy.getImagePath());
                gymBuddyMap.put("requiresAllAchievements", gymBuddy.isRequiresAllAchievements());
                gymBuddyMap.put("requiresAllDailyAchievements", gymBuddy.isRequiresAllDailyAchievements());
                // Only available if it doesn't require achievements, or if the required achievements are complete
                boolean isAvailable = (!gymBuddy.isRequiresAllAchievements() || allAchievementsComplete) &&
                                      (!gymBuddy.isRequiresAllDailyAchievements() || allDailyAchievementsComplete);
                gymBuddyMap.put("isAvailable", isAvailable);
                return gymBuddyMap;
            })
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(gymBuddiesWithAvailability);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymBuddy> getGymBuddyById(@PathVariable Long id) {
        return ResponseEntity.ok(gymBuddyService.getGymBuddyById(id));
    }
}
