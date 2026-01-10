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

    // Default constructor (required by JPA)
    public GymBuddy() {
    }

    public GymBuddy(String name, int coinCost, String imagePath) {
        this.name = name;
        this.coinCost = coinCost;
        this.imagePath = imagePath;
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
}
