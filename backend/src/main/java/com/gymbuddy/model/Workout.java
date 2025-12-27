package com.gymbuddy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "workouts")
public class Workout {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String workoutType; // ARMS, BACK, CHEST, LEGS
    
    @Column(nullable = false)
    private String intensity; // LOW, MEDIUM, HIGH, MAX
    
    @Column(nullable = false)
    private String duration; // 30s, 1m, 2m, 3m
    
    @Column(nullable = false)
    private LocalDateTime completedAt;
    
    @Column(nullable = false)
    private Integer durationSeconds; // Duration in seconds for easier querying
    
    // Default constructor (required by JPA)
    public Workout() {
    }
    
    // Constructor
    public Workout(String workoutType, String intensity, String duration, LocalDateTime completedAt, Integer durationSeconds) {
        this.workoutType = workoutType;
        this.intensity = intensity;
        this.duration = duration;
        this.completedAt = completedAt;
        this.durationSeconds = durationSeconds;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getWorkoutType() {
        return workoutType;
    }
    
    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }
    
    public String getIntensity() {
        return intensity;
    }
    
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
    
    public Integer getDurationSeconds() {
        return durationSeconds;
    }
    
    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}

