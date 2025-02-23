package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest

class JWTServiceTest {

    @Autowired
    private JWTService jwtService;

    @Test
    void generateToken_fromUser(){
        final Set<GrantedAuthority> authorities = new HashSet<>(
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                )
        );

        User user = new User("purushottam6761", "Uni8113@48", true, true, true, true, authorities);
        String token = jwtService.generateToken(user);
        System.out.println("Generated Token From User: "+token);
    }

    @Test
    void generateToken_fromEntity(){
        MembershipEntity membership = MembershipEntity.builder()
                .appid(1L)
                .fname("Purushottam")
                .username("Purushottam")
                .designation("desg")
                .password("Pass")
        .build();
        String token = jwtService.generateToken(membership);
        System.out.println("Generated Token From Entity: "+token);
        }
    }