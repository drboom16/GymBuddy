package com.gymbuddy.model.Exercises;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import com.gymbuddy.model.ExerciseSet;
import java.util.*;

@Embeddable
public class Exercise {
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private int noSets;
    
    @Column(nullable = false)
    private int currSets = 0;
    
    @Column(nullable = false)
    private String type;

    // Store sets completion as a comma-separated string (e.g., "true,false,true")
    // This is a workaround since @ElementCollection cannot be used in an Embeddable
    @Column(length = 1000)
    private String setsCompletion;

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

    public int getCurrSets() {
        return currSets;
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

    // Helper methods for managing sets
    public List<ExerciseSet> getSets() {
        List<ExerciseSet> sets = new ArrayList<>();
        if (setsCompletion != null && !setsCompletion.isEmpty()) {
            String[] completions = setsCompletion.split(",");
            for (String completion : completions) {
                sets.add(new ExerciseSet(Boolean.parseBoolean(completion.trim())));
            }
        } else {
            // Initialize with all false if not set
            for (int i = 0; i < noSets; i++) {
                sets.add(new ExerciseSet(false));
            }
        }
        return sets;
    }

    public void setSets(List<ExerciseSet> sets) {
        if (sets == null || sets.isEmpty()) {
            this.setsCompletion = null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < sets.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(sets.get(i).isCompleted());
            }
            this.setsCompletion = sb.toString();
        }
    }

    public void markSetComplete(int setIndex) {
        List<ExerciseSet> sets = getSets();
        // Ensure we have enough sets
        while (sets.size() <= setIndex) {
            sets.add(new ExerciseSet(false));
        }
        sets.get(setIndex).setCompleted(true);
        setSets(sets);
    }

    public boolean isSetComplete(int setIndex) {
        List<ExerciseSet> sets = getSets();
        if (setIndex >= sets.size()) {
            return false;
        }
        return sets.get(setIndex).isCompleted();
    }
}
