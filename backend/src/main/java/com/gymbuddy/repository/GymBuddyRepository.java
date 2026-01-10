package com.gymbuddy.repository;

import com.gymbuddy.model.GymBuddy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymBuddyRepository extends JpaRepository<GymBuddy, Long> {
}
