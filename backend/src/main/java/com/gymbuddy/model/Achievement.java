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
    private Integer duration; // Optional tag: duration in seconds

    @Column(nullable = false)
    private int coinReward = 0; // Coin reward for completing this achievement

    @Column(nullable = false)
    private boolean isDaily = false; // Whether this is a daily achievement (true) or lifetime achievement (false)


    public Achievement() { // Default constructor (required by JPA)

    }

    public Achievement(String achievementName, int target) {
        this.achievementName = achievementName;
        this.target = target;
    }

    public Achievement(String achievementName, int target, int coinReward) {
        this.achievementName = achievementName;
        this.target = target;
        this.coinReward = coinReward;
    }

    public Achievement(String achievementName, int target, String exerciseType, Integer duration) {
        this.achievementName = achievementName;
        this.target = target;
        this.exerciseType = exerciseType;
        this.duration = duration;
    }

    public Achievement(String achievementName, int target, int coinReward, String exerciseType, Integer duration) {
        this.achievementName = achievementName;
        this.target = target;
        this.coinReward = coinReward;
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.isDaily = false;
    }

    public Achievement(String achievementName, int target, int coinReward, boolean isDaily) {
        this.achievementName = achievementName;
        this.target = target;
        this.coinReward = coinReward;
        this.isDaily = isDaily;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public int getCoinReward() {
        return coinReward;
    }

    public void setCoinReward(int coinReward) {
        this.coinReward = coinReward;
    }

    public boolean isDaily() {
        return isDaily;
    }

    public void setDaily(boolean daily) {
        isDaily = daily;
    }
}
