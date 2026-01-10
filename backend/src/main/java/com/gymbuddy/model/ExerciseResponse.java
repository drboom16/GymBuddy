package com.gymbuddy.model;

public class ExerciseResponse {
    private final String type;
    private final int duration;

    public ExerciseResponse(String type, int duration) {
        this.type = type;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }
}
