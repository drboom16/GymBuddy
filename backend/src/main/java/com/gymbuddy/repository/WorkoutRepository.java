package com.gymbuddy.repository;

import com.gymbuddy.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    // Find all workouts ordered by ID ascending
    List<Workout> findAllByOrderByIdAsc();
}

