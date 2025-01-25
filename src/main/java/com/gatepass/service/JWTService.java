package com.gatepass.service;

import com.gatepass.models.StaffEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;
    @PostConstruct
    public void init() {
        System.out.println("JWT Secret Key: " + jwtSecretKey);
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(StaffEntity staffEntity){
        return Jwts.builder()
                .setSubject(staffEntity.getUsername())
                .claim("email", staffEntity.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+1000*60)))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String Token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(Token)
                .getBody();

        return String.valueOf(claims.getSubject());
    }
}
