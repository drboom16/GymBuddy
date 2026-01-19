package com.gymbuddy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GymBuddyTest {

    @Test
    void testGymBuddyCreation_WithBasicParameters() {
        GymBuddy gymBuddy = new GymBuddy("EarthBuddy", 600, "/public/shop/gymbuddy.png");

        assertEquals("EarthBuddy", gymBuddy.getName());
        assertEquals(600, gymBuddy.getCoinCost());
        assertEquals("/public/shop/gymbuddy.png", gymBuddy.getImagePath());
        assertFalse(gymBuddy.isRequiresAllAchievements());
        assertFalse(gymBuddy.isRequiresAllDailyAchievements());
    }

    @Test
    void testGymBuddyCreation_WithAchievementRequirement() {
        GymBuddy gymBuddy = new GymBuddy("LegendaryBuddy", 0, "/public/shop/legend.png", true);

        assertTrue(gymBuddy.isRequiresAllAchievements());
        assertFalse(gymBuddy.isRequiresAllDailyAchievements());
    }

    @Test
    void testGymBuddyCreation_WithAllParameters() {
        GymBuddy gymBuddy = new GymBuddy("MysticalBuddy", 3000, "/public/shop/mystical.png", 
                true, true);

        assertTrue(gymBuddy.isRequiresAllAchievements());
        assertTrue(gymBuddy.isRequiresAllDailyAchievements());
    }

    @Test
    void testGettersAndSetters() {
        GymBuddy gymBuddy = new GymBuddy();

        gymBuddy.setId(1L);
        gymBuddy.setName("TestBuddy");
        gymBuddy.setCoinCost(500);
        gymBuddy.setImagePath("/test/path.png");
        gymBuddy.setRequiresAllAchievements(true);
        gymBuddy.setRequiresAllDailyAchievements(true);

        assertEquals(1L, gymBuddy.getId());
        assertEquals("TestBuddy", gymBuddy.getName());
        assertEquals(500, gymBuddy.getCoinCost());
        assertEquals("/test/path.png", gymBuddy.getImagePath());
        assertTrue(gymBuddy.isRequiresAllAchievements());
        assertTrue(gymBuddy.isRequiresAllDailyAchievements());
    }
}
