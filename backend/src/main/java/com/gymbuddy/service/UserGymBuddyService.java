package com.gymbuddy.service;

import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.model.User;
import com.gymbuddy.model.UserGymBuddy;
import com.gymbuddy.repository.GymBuddyRepository;
import com.gymbuddy.repository.UserGymBuddyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserGymBuddyService {
    
    private final UserGymBuddyRepository userGymBuddyRepository;
    private final GymBuddyRepository gymBuddyRepository;
    private final UserService userService;
    
    public UserGymBuddyService(UserGymBuddyRepository userGymBuddyRepository, 
                              GymBuddyRepository gymBuddyRepository,
                              UserService userService) {
        this.userGymBuddyRepository = userGymBuddyRepository;
        this.gymBuddyRepository = gymBuddyRepository;
        this.userService = userService;
    }
    
    /**
     * Get all gymbuddies owned by the user
     */
    public List<Map<String, Object>> getUserGymBuddies() {
        User user = userService.getUser();
        List<UserGymBuddy> userGymBuddies = userGymBuddyRepository.findByUser(user);
        
        return userGymBuddies.stream()
            .map(ugb -> {
                Map<String, Object> map = new java.util.HashMap<>();
                GymBuddy gb = ugb.getGymBuddy();
                map.put("id", gb.getId());
                map.put("name", gb.getName());
                map.put("imagePath", gb.getImagePath());
                map.put("isActive", ugb.isActive());
                return map;
            })
            .collect(Collectors.toList());
    }
    
    /**
     * Set a gymbuddy as active (deactivates all others)
     */
    @Transactional
    public void setActiveGymBuddy(Long gymBuddyId) {
        User user = userService.getUser();
        
        // Check if user owns this gymbuddy
        UserGymBuddy userGymBuddy = userGymBuddyRepository
            .findByUserAndGymBuddyId(user, gymBuddyId)
            .orElseThrow(() -> new RuntimeException("User does not own this gymbuddy"));
        
        // Deactivate all gymbuddies for this user
        List<UserGymBuddy> allUserGymBuddies = userGymBuddyRepository.findByUser(user);
        for (UserGymBuddy ugb : allUserGymBuddies) {
            ugb.setActive(false);
        }
        
        // Activate the selected one
        userGymBuddy.setActive(true);
        userGymBuddyRepository.saveAll(allUserGymBuddies);
    }
    
    /**
     * Purchase and add a gymbuddy to user's collection
     */
    @Transactional
    public boolean purchaseGymBuddy(Long gymBuddyId) {
        User user = userService.getUser();
        GymBuddy gymBuddy = gymBuddyRepository.findById(gymBuddyId)
            .orElseThrow(() -> new RuntimeException("GymBuddy not found"));
        
        // Check if user already owns this gymbuddy
        Optional<UserGymBuddy> existing = userGymBuddyRepository
            .findByUserAndGymBuddyId(user, gymBuddyId);
        
        if (existing.isPresent()) {
            throw new RuntimeException("User already owns this gymbuddy");
        }
        
        // Check if user has enough coins
        if (user.getCoins() < gymBuddy.getCoinCost()) {
            throw new RuntimeException("Insufficient coins");
        }
        
        // Deduct coins
        boolean coinsDeducted = userService.deductCoins(gymBuddy.getCoinCost());
        if (!coinsDeducted) {
            throw new RuntimeException("Failed to deduct coins");
        }
        
        // Add gymbuddy to user's collection (not active by default)
        UserGymBuddy userGymBuddy = new UserGymBuddy(user, gymBuddy, false);
        userGymBuddyRepository.save(userGymBuddy);
        
        return true;
    }
    
    /**
     * Get the active gymbuddy for the user
     */
    public Map<String, Object> getActiveGymBuddy() {
        User user = userService.getUser();
        Optional<UserGymBuddy> activeGymBuddy = userGymBuddyRepository.findByUserAndIsActiveTrue(user);
        
        if (activeGymBuddy.isPresent()) {
            GymBuddy gb = activeGymBuddy.get().getGymBuddy();
            Map<String, Object> map = new java.util.HashMap<>();
            map.put("id", gb.getId());
            map.put("name", gb.getName());
            map.put("imagePath", gb.getImagePath());
            return map;
        }
        
        // Return default if no active gymbuddy
        return null;
    }
    
    /**
     * Initialize default white GymBuddy for new users
     * This should be called when a user is first created
     */
    @Transactional
    public void initializeDefaultGymBuddy() {
        User user = userService.getUser();
        List<UserGymBuddy> userGymBuddies = userGymBuddyRepository.findByUser(user);
        
        // If user has no gymbuddies, give them the default white one
        if (userGymBuddies.isEmpty()) {
            // Find or create default white GymBuddy
            // For now, we'll use the first gymbuddy or create a default one
            // Assuming "GymBuddy" is the default white one - we may need to add this to DataInitializer
            List<GymBuddy> allGymBuddies = gymBuddyRepository.findAll();
            GymBuddy defaultGymBuddy = allGymBuddies.stream()
                .filter(gb -> "GymBuddy".equals(gb.getName()))
                .findFirst()
                .orElse(null);
            
            // If default doesn't exist, we'll use the first available or create it
            // For now, let's assume we need to add it to the database
            // This will be handled in the controller when getting user gymbuddies
        }
    }
}
