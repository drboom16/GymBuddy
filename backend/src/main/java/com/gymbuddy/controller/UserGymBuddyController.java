package com.gymbuddy.controller;

import com.gymbuddy.service.UserGymBuddyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/gymbuddies")
public class UserGymBuddyController {
    
    private final UserGymBuddyService userGymBuddyService;
    
    public UserGymBuddyController(UserGymBuddyService userGymBuddyService) {
        this.userGymBuddyService = userGymBuddyService;
    }
    
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getUserGymBuddies() {
        try {
            List<Map<String, Object>> gymbuddies = userGymBuddyService.getUserGymBuddies();
            return ResponseEntity.ok(gymbuddies);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/{gymBuddyId}/set-active")
    public ResponseEntity<Void> setActiveGymBuddy(@PathVariable Long gymBuddyId) {
        try {
            userGymBuddyService.setActiveGymBuddy(gymBuddyId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/{gymBuddyId}/purchase")
    public ResponseEntity<Map<String, Object>> purchaseGymBuddy(@PathVariable Long gymBuddyId) {
        try {
            boolean success = userGymBuddyService.purchaseGymBuddy(gymBuddyId);
            if (success) {
                Map<String, Object> response = new java.util.HashMap<>();
                response.put("success", true);
                response.put("message", "Purchase successful!");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (RuntimeException e) {
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/active")
    public ResponseEntity<Map<String, Object>> getActiveGymBuddy() {
        try {
            Map<String, Object> activeGymBuddy = userGymBuddyService.getActiveGymBuddy();
            return ResponseEntity.ok(activeGymBuddy);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
