package com.gymbuddy.model;

import jakarta.persistence.*;

@Entity
public class GymBuddy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int coinCost;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private boolean requiresAllAchievements = false; // Whether this gymbuddy requires all achievements to be completed

    // Default constructor (required by JPA)
    public GymBuddy() {
    }

    public GymBuddy(String name, int coinCost, String imagePath) {
        this.name = name;
        this.coinCost = coinCost;
        this.imagePath = imagePath;
    }

    public GymBuddy(String name, int coinCost, String imagePath, boolean requiresAllAchievements) {
        this.name = name;
        this.coinCost = coinCost;
        this.imagePath = imagePath;
        this.requiresAllAchievements = requiresAllAchievements;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoinCost() {
        return coinCost;
    }

    public void setCoinCost(int coinCost) {
        this.coinCost = coinCost;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isRequiresAllAchievements() {
        return requiresAllAchievements;
    }

    public void setRequiresAllAchievements(boolean requiresAllAchievements) {
        this.requiresAllAchievements = requiresAllAchievements;
    }
}
