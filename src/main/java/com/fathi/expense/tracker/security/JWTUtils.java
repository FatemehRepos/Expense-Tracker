package com.fathi.expense.tracker.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class JWTUtils {

    private final JwtConfig config;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + config.getExpirationTime()))
                .signWith(Keys.hmacShaKeyFor(config.getSecretKey().getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return isValidTokenUsername(token, userDetails) && isExpiredToken(token);
    }

    public Date extractExpirsionDate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(config.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(config.getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean isValidTokenUsername(String token, UserDetails userDetails) {
        return extractUsername(userDetails.getUsername()).equals(userDetails.getUsername());
    }

    private boolean isExpiredToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(config.getSecretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}
