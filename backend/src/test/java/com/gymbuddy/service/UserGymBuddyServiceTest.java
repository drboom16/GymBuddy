package com.gymbuddy.service;

import com.gymbuddy.model.GymBuddy;
import com.gymbuddy.model.User;
import com.gymbuddy.model.UserGymBuddy;
import com.gymbuddy.repository.GymBuddyRepository;
import com.gymbuddy.repository.UserGymBuddyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGymBuddyServiceTest {

    @Mock
    private UserGymBuddyRepository userGymBuddyRepository;

    @Mock
    private GymBuddyRepository gymBuddyRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserGymBuddyService userGymBuddyService;

    private User testUser;
    private GymBuddy testGymBuddy;
    private UserGymBuddy testUserGymBuddy;

    @BeforeEach
    void setUp() {
        testUser = new User(600);
        testUser.setId(1L);

        testGymBuddy = new GymBuddy("EarthBuddy", 600, "/public/shop/gymbuddy-rookie.png");
        testGymBuddy.setId(1L);

        testUserGymBuddy = new UserGymBuddy(testUser, testGymBuddy, false);
        testUserGymBuddy.setId(1L);
    }

    @Test
    void testGetUserGymBuddies_ReturnsListOfGymbuddies() {
        // Given
        when(userService.getUser()).thenReturn(testUser);
        when(userGymBuddyRepository.findByUser(testUser)).thenReturn(List.of(testUserGymBuddy));

        // When
        List<java.util.Map<String, Object>> result = userGymBuddyService.getUserGymBuddies();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("EarthBuddy", result.get(0).get("name"));
        assertEquals(false, result.get(0).get("isActive"));
    }

    @Test
    void testSetActiveGymBuddy_SetsGymBuddyAsActive() {
        // Given
        UserGymBuddy inactiveGymBuddy = new UserGymBuddy(testUser, testGymBuddy, false);
        when(userService.getUser()).thenReturn(testUser);
        when(userGymBuddyRepository.findByUserAndGymBuddyId(testUser, 1L))
                .thenReturn(Optional.of(testUserGymBuddy));
        when(userGymBuddyRepository.findByUser(testUser))
                .thenReturn(List.of(testUserGymBuddy, inactiveGymBuddy));
        when(userGymBuddyRepository.saveAll(any())).thenReturn(List.of(testUserGymBuddy, inactiveGymBuddy));

        // When
        userGymBuddyService.setActiveGymBuddy(1L);

        // Then
        assertTrue(testUserGymBuddy.isActive());
        verify(userGymBuddyRepository).saveAll(any());
    }

    @Test
    void testSetActiveGymBuddy_WhenUserDoesNotOwn_ThrowsException() {
        // Given
        when(userService.getUser()).thenReturn(testUser);
        when(userGymBuddyRepository.findByUserAndGymBuddyId(testUser, 1L))
                .thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> userGymBuddyService.setActiveGymBuddy(1L));
    }

    @Test
    void testPurchaseGymBuddy_SuccessfullyPurchases() {
        // Given
        when(userService.getUser()).thenReturn(testUser);
        when(gymBuddyRepository.findById(1L)).thenReturn(Optional.of(testGymBuddy));
        when(userGymBuddyRepository.findByUserAndGymBuddyId(testUser, 1L))
                .thenReturn(Optional.empty());
        when(userService.deductCoins(600)).thenReturn(true);
        when(userGymBuddyRepository.save(any(UserGymBuddy.class))).thenReturn(testUserGymBuddy);

        // When
        boolean result = userGymBuddyService.purchaseGymBuddy(1L);

        // Then
        assertTrue(result);
        verify(userService).deductCoins(600);
        verify(userGymBuddyRepository).save(any(UserGymBuddy.class));
    }

    @Test
    void testPurchaseGymBuddy_WhenAlreadyOwned_ThrowsException() {
        // Given
        when(userService.getUser()).thenReturn(testUser);
        when(gymBuddyRepository.findById(1L)).thenReturn(Optional.of(testGymBuddy));
        when(userGymBuddyRepository.findByUserAndGymBuddyId(testUser, 1L))
                .thenReturn(Optional.of(testUserGymBuddy));

        // When & Then
        assertThrows(RuntimeException.class, () -> userGymBuddyService.purchaseGymBuddy(1L),
                "User already owns this gymbuddy");
    }

    @Test
    void testPurchaseGymBuddy_WhenInsufficientCoins_ThrowsException() {
        // Given
        testUser.setCoins(100);
        when(userService.getUser()).thenReturn(testUser);
        when(gymBuddyRepository.findById(1L)).thenReturn(Optional.of(testGymBuddy));
        when(userGymBuddyRepository.findByUserAndGymBuddyId(testUser, 1L))
                .thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> userGymBuddyService.purchaseGymBuddy(1L),
                "Insufficient coins");
    }

    @Test
    void testGetActiveGymBuddy_ReturnsActiveGymBuddy() {
        // Given
        testUserGymBuddy.setActive(true);
        when(userService.getUser()).thenReturn(testUser);
        when(userGymBuddyRepository.findByUserAndIsActiveTrue(testUser))
                .thenReturn(Optional.of(testUserGymBuddy));

        // When
        java.util.Map<String, Object> result = userGymBuddyService.getActiveGymBuddy();

        // Then
        assertNotNull(result);
        assertEquals("EarthBuddy", result.get("name"));
        assertEquals(1L, result.get("id"));
    }

    @Test
    void testGetActiveGymBuddy_WhenNoActive_ReturnsNull() {
        // Given
        when(userService.getUser()).thenReturn(testUser);
        when(userGymBuddyRepository.findByUserAndIsActiveTrue(testUser))
                .thenReturn(Optional.empty());

        // When
        java.util.Map<String, Object> result = userGymBuddyService.getActiveGymBuddy();

        // Then
        assertNull(result);
    }
}
