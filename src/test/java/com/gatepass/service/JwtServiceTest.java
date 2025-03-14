package com.gatepass.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        jwtService.secretKey = secretKey;  // Inject secretKey for testing
    }

    @Test
    void testGenerateToken() {
        String username = "testUser";
        String token = jwtService.generateToken(username);

        assertNotNull(token, "Token should not be null");
        System.out.println("Generated Token: " + token);
    }

//    @Test
//    void testValidateToken() {
//        String username = "testUser";
//        String token = jwtService.generateToken(username);
//
//        assertTrue(jwtService.validateToken(token, username), "Token should be valid");
//    }
//
//    @Test
//    void testExtractUsername() {
//        String username = "testUser";
//        String token = jwtService.generateToken(username);
//
//        String extractedUsername = jwtService.extractUsername(token);
//        assertEquals(username, extractedUsername, "Extracted username should match");
//    }
//
//    @Test
//    void testTokenExpiration() throws InterruptedException {
//        String username = "testUser";
//        String token = jwtService.generateToken(username);
//
//        Thread.sleep(1000);  // Simulate a short delay
//        assertFalse(jwtService.validateToken(token, "wrongUser"), "Wrong username should invalidate the token");
//    }
}
