package com.gymbuddy.service;

import com.gymbuddy.model.User;
import com.gymbuddy.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(100);
        testUser.setId(1L);
    }

    @Test
    void testGetOrCreateUser_WhenNoUsersExist_CreatesNewUser() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User result = userService.getOrCreateUser();

        assertNotNull(result);
        assertEquals(0, result.getCoins());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testGetOrCreateUser_WhenUserExists_ReturnsExistingUser() {
        List<User> users = List.of(testUser);
        when(userRepository.findAll()).thenReturn(users);

        User result = userService.getOrCreateUser();

        assertNotNull(result);
        assertEquals(100, result.getCoins());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetCoins_ReturnsUserCoins() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        int coins = userService.getCoins();

        assertEquals(100, coins);
    }

    @Test
    void testAddCoins_IncreasesUserCoins() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        userService.addCoins(50);

        assertEquals(150, testUser.getCoins());
        verify(userRepository).save(testUser);
    }

    @Test
    void testDeductCoins_WhenSufficientCoins_ReturnsTrue() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        boolean result = userService.deductCoins(50);

        assertTrue(result);
        assertEquals(50, testUser.getCoins());
        verify(userRepository).save(testUser);
    }

    @Test
    void testDeductCoins_WhenInsufficientCoins_ReturnsFalse() {
        when(userRepository.findAll()).thenReturn(List.of(testUser));

        boolean result = userService.deductCoins(150);

        assertFalse(result);
        assertEquals(100, testUser.getCoins());
        verify(userRepository, never()).save(any(User.class));
    }
}
