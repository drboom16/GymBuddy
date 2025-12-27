package com.gymbuddy.repository;

import com.gymbuddy.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    
    // Find all workouts ordered by completion date (newest first)
    List<Workout> findAllByOrderByCompletedAtDesc();
    
    // Find workouts within a date range
    List<Workout> findByCompletedAtBetweenOrderByCompletedAtDesc(LocalDateTime start, LocalDateTime end);
    
    // Find workouts by intensity
    List<Workout> findByIntensityOrderByCompletedAtDesc(String intensity);
}

