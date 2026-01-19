package com.gymbuddy.controller;

import com.gymbuddy.service.UserGymBuddyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserGymBuddyControllerTest {

    @Mock
    private UserGymBuddyService userGymBuddyService;

    @InjectMocks
    private UserGymBuddyController userGymBuddyController;

    @Test
    void testGetUserGymBuddies_ReturnsList() {
        Map<String, Object> gymbuddyData = new HashMap<>();
        gymbuddyData.put("id", 1L);
        gymbuddyData.put("name", "EarthBuddy");
        gymbuddyData.put("isActive", true);
        when(userGymBuddyService.getUserGymBuddies()).thenReturn(List.of(gymbuddyData));

        ResponseEntity<List<Map<String, Object>>> response = userGymBuddyController.getUserGymBuddies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("EarthBuddy", response.getBody().get(0).get("name"));
    }

    @Test
    void testSetActiveGymBuddy_Success() {
        doNothing().when(userGymBuddyService).setActiveGymBuddy(1L);

        ResponseEntity<Void> response = userGymBuddyController.setActiveGymBuddy(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userGymBuddyService).setActiveGymBuddy(1L);
    }

    @Test
    void testPurchaseGymBuddy_Success() {
        when(userGymBuddyService.purchaseGymBuddy(1L)).thenReturn(true);

        ResponseEntity<Map<String, Object>> response = userGymBuddyController.purchaseGymBuddy(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue((Boolean) response.getBody().get("success"));
    }

    @Test
    void testPurchaseGymBuddy_WhenAlreadyOwned_ReturnsBadRequest() {
        when(userGymBuddyService.purchaseGymBuddy(1L))
                .thenThrow(new RuntimeException("User already owns this gymbuddy"));

        ResponseEntity<Map<String, Object>> response = userGymBuddyController.purchaseGymBuddy(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse((Boolean) response.getBody().get("success"));
    }

    @Test
    void testGetActiveGymBuddy_ReturnsActiveGymBuddy() {
        Map<String, Object> activeGymBuddy = new HashMap<>();
        activeGymBuddy.put("id", 1L);
        activeGymBuddy.put("name", "EarthBuddy");
        when(userGymBuddyService.getActiveGymBuddy()).thenReturn(activeGymBuddy);

        ResponseEntity<Map<String, Object>> response = userGymBuddyController.getActiveGymBuddy();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("EarthBuddy", response.getBody().get("name"));
    }
}
