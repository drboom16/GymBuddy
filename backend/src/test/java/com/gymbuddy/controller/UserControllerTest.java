package com.gymbuddy.controller;

import com.gymbuddy.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetCoins_ReturnsCoins() {
        when(userService.getCoins()).thenReturn(500);

        ResponseEntity<Map<String, Object>> response = userController.getCoins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(500, response.getBody().get("coins"));
    }

    @Test
    void testGetCoins_ReturnsZeroCoins() {
        when(userService.getCoins()).thenReturn(0);

        ResponseEntity<Map<String, Object>> response = userController.getCoins();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().get("coins"));
    }
}
