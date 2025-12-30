package com.gymbuddy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Exercise {
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private int noSets;
    
    @Column(nullable = true)
    private String type; // chest, back, arms, legs

    // Default constructor (required by JPA)
    public Exercise() {
    }

    public Exercise(String name, int noSets) {
        this.name = name;
        this.noSets = noSets;
    }
    
    public Exercise(String name, int noSets, String type) {
        this.name = name;
        this.noSets = noSets;
        this.type = type;
    }

    public void setNoSets(int noSets) {
        this.noSets = noSets;
    }

    public int getNoSets() {
        return noSets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
