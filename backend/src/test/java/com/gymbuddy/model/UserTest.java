package com.gymbuddy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation_WithCoins() {
        User user = new User(100);
        assertEquals(100, user.getCoins());
    }

    @Test
    void testUserCreation_DefaultConstructor() {
        User user = new User();
        assertEquals(0, user.getCoins());
    }

    @Test
    void testAddCoins_IncreasesCoins() {
        User user = new User(50);
        user.addCoins(25);
        assertEquals(75, user.getCoins());
    }

    @Test
    void testSetCoins_UpdatesCoins() {
        User user = new User(50);
        user.setCoins(200);
        assertEquals(200, user.getCoins());
    }

    @Test
    void testGettersAndSetters() {
        User user = new User();
        user.setId(1L);
        user.setCoins(300);
        assertEquals(1L, user.getId());
        assertEquals(300, user.getCoins());
    }
}
