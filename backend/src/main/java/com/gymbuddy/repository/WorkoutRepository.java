package com.gymbuddy.repository;

import com.gymbuddy.model.Workout;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    // Find all workouts ordered by ID ascending
    List<Workout> findAllByOrderByIdAsc();
    
    // Find the last 3 completed workouts, ordered by completion date descending
    // This query selects workouts where completedAt is not null, orders them by completedAt descending,
    // and limits the result to 3 records using Pageable
    @Query("SELECT w FROM Workout w WHERE w.completedAt IS NOT NULL ORDER BY w.completedAt DESC")
    List<Workout> findLast3CompletedWorkouts(Pageable pageable);
}

