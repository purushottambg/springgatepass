package com.gatepass.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class JWTServiceTest {

    @Autowired
    private JWTService jwtService;

    @Test
    void generateToken(){
        final Set<GrantedAuthority> authorities = new HashSet<>(
                Arrays.asList(
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_ADMIN")
                )
        );

        User user = new User("purushottam6761", "Uni8113@48", true, true, true, true, authorities);
        String token = jwtService.generateToken(user);
        System.out.println("Generated Token: "+token);
    }
}