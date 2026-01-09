package com.gymbuddy.model;

import jakarta.persistence.*;

@Entity
public class Achievement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String achievementName; // The name of the achievement on the frontend

    @Column (nullable = false)
    private int target; // The target no. times needed for this achievement to be completed

    @Column
    private int current = 0; // The current count for this achievement

    @Column
    private String exerciseType; // Optional tag: exercise type (e.g., "arms", "chest", "legs")

    @Column
    private String intensity; // Optional tag: intensity (e.g., "low", "medium", "high", "max")

    @Column
    private Integer duration; // Optional tag: duration in seconds


    public Achievement() { // Default constructor (required by JPA)

    }

    public Achievement(String achievementName, int target) {
        this.achievementName = achievementName;
        this.target = target;
    }

    public Achievement(String achievementName, int target, String exerciseType, String intensity, Integer duration) {
        this.achievementName = achievementName;
        this.target = target;
        this.exerciseType = exerciseType;
        this.intensity = intensity;
        this.duration = duration;
    }

    public void incrementCurrent() {
        current += 1;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public int getTarget() {
        return target;
    }

    public Long getId() {
        return id;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
