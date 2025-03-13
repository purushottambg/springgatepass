package com.gatepass.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    private final String jwtSecretKey = "ads9f6askj3h4k1hf86asdfiahkjh34a789s6df89ayshkjh3jkh786adsf78ay";
    Logger logger = LoggerFactory.getLogger(JWTService.class);

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 3))
                .signWith(getSecretKey())
                .compact();
        logger.info("Created token id: {}",token);
        return token;

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
}


/*package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    //@Value("${jwt.secretKey}")
    private String jwtSecretKey="ads9f6askj3h4k1hf86asdfiahkjh34a789s6df89ayshkjh3jkh786adsf78ay";

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    //Function to generate the JWT token for the user shared to the function.
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+1000*60*3)))
                .signWith(getSecretKey())
                .compact();
    }

    //retrieve the username from the given token
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

    //Function to generate the JWT token for the user shared to the function.
    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("username", user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+1000*60)))
                .signWith(getSecretKey())
                .compact();
    }

    //Function to generate the JWT token for the user shared to the function.
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*5))
                .signWith(getSecretKey())
                .compact();
    }

    //Parser is used to access the user details from the given token
    public String getUsernameFromToken(String Token) throws Exception{
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(Token)
                .getBody();
        return String.valueOf(claims.getSubject());
    }
}*/