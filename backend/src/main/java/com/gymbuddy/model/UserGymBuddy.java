package com.gymbuddy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_gymbuddy")
public class UserGymBuddy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "gymbuddy_id", nullable = false)
    private GymBuddy gymBuddy;
    
    @Column(nullable = false)
    private boolean isActive = false;
    
    // Default constructor (required by JPA)
    public UserGymBuddy() {
    }
    
    public UserGymBuddy(User user, GymBuddy gymBuddy, boolean isActive) {
        this.user = user;
        this.gymBuddy = gymBuddy;
        this.isActive = isActive;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public GymBuddy getGymBuddy() {
        return gymBuddy;
    }
    
    public void setGymBuddy(GymBuddy gymBuddy) {
        this.gymBuddy = gymBuddy;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
}
