package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ActiveProfiles;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

    @Mock
    private StaffRepo staffRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private StaffService staffService;

    @Test
    void loginStaff_ShouldReturnToken() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName("testUser");
        loginDTO.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(jwtService.generateToken(user));

        // Act
        String token = staffService.loginStaff(loginDTO);
        System.out.println("Generated toke value is: "+token);
        // Assert

    }
}
