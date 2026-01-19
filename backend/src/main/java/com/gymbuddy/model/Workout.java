package com.gymbuddy.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import com.gymbuddy.model.Exercises.*;

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

    @Column
    private LocalDateTime completedAt;

    @Column
    private String color; // Color hex code for the workout card

    // Default constructor (required by JPA)
    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public Workout(String workoutName, List<Exercise> exercises) {
        this.workoutName = workoutName;
        this.exercises = new ArrayList<>(exercises);
    }

    public Workout(String workoutName, List<Exercise> exercises, String color) {
        this.workoutName = workoutName;
        this.exercises = new ArrayList<>(exercises);
        this.color = color;
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

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
