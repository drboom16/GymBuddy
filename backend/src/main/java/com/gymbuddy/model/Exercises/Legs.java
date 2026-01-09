package com.gymbuddy.model.Exercises;

public class Legs extends Exercise {
    // Default constructor (required by JPA)
    public Legs() {
        super();
        setType("legs");
    }
    
    public Legs(String name, int noSets) {
        super(name, noSets, "legs");
    }
}
