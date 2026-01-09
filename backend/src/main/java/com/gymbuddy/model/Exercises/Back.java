package com.gymbuddy.model.Exercises;

public class Back extends Exercise {
    // Default constructor (required by JPA)
    public Back() {
        super();
        setType("back");
    }
    
    public Back(String name, int noSets) {
        super(name, noSets, "back");
    }
}
