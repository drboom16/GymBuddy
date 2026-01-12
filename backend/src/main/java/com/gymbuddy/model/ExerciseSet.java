package com.gymbuddy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ExerciseSet {

    @Column(nullable = false)
    private boolean completed = false;

    public ExerciseSet() {
    }

    public ExerciseSet(boolean completed) {
        this.completed = completed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
