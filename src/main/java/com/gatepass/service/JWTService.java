package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    //@Value("${jwt.secretKey}")
    private static final String jwtSecretKey="ads9f6askj3h4k1hf86asdfiahkjh34a789s6df89ayshkjh3jkh786adsf78ay";

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+1000*60*3)))
                .signWith(getSecretKey())
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public String generateToken(MembershipEntity membershipEntity){
        return Jwts.builder()
                .setSubject(membershipEntity.getUsername())
                .claim("email", membershipEntity.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+1000*60)))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(getSecretKey())
                .compact();
    }

    public String getUsernameFromToken(String Token) throws Exception{
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(Token)
                .getBody();
        return String.valueOf(claims.getSubject());
    }
}
