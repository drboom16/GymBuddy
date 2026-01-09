package com.gymbuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymbuddy.model.Achievement;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {

}
