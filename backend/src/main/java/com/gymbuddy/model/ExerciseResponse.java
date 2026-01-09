package com.gymbuddy.model;

public class ExerciseResponse {
    private final String type;
    private final String intensity;
    private final int duration;

    public ExerciseResponse(String type, String intensity, int duration) {
        this.type = type;
        this.intensity = intensity;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public String getIntensity() {
        return intensity;
    }

    public int getDuration() {
        return duration;
    }
}
