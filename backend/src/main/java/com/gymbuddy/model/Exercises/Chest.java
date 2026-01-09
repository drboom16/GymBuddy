package com.gymbuddy.model.Exercises;

public class Chest extends Exercise {
    // Default constructor (required by JPA)
    public Chest() {
        super();
        setType("chest");
    }
    
    public Chest(String name, int noSets) {
        super(name, noSets, "chest");
    }
}
