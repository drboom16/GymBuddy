package com.gymbuddy.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String workoutName;

    @ElementCollection
    @CollectionTable(name = "workout_exercises", joinColumns = @JoinColumn(name = "workout_id"))
    private List<Exercise> exercises;

    // Default constructor (required by JPA)
    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public Workout(String workoutName, List<Exercise> exercises) {
        this.workoutName = workoutName;
        this.exercises = new ArrayList<>(exercises);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
