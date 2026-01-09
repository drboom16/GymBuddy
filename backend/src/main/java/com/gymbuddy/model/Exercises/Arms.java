package com.gymbuddy.model.Exercises;

public class Arms extends Exercise {
    // Default constructor (required by JPA)
    public Arms() {
        super();
        setType("arms");
    }
    
    public Arms(String name, int noSets) {
        super(name, noSets, "arms");
    }
}
