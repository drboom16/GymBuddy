package com.gymbuddy.service;

import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.repository.GymBuddyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymBuddyService {
    
    private final GymBuddyRepository gymBuddyRepository;

    public GymBuddyService(GymBuddyRepository gymBuddyRepository) {
        this.gymBuddyRepository = gymBuddyRepository;
    }

    public List<GymBuddy> getAllGymBuddies() {
        return gymBuddyRepository.findAll();
    }

    public GymBuddy getGymBuddyById(Long id) {
        return gymBuddyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("GymBuddy not found with id: " + id));
    }
}
