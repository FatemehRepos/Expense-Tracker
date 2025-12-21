package com.fathi.expense.tracker.security;

import io.jsonwebtoken.*;
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

    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + config.getExpirationMinutes()))
                .signWith(Keys.hmacShaKeyFor(config.getSecretKey().getBytes(StandardCharsets.UTF_8)),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return isValidTokenUsername(userDetails) && isExpiredToken(token);
    }
//
//    public String validateResetToken(String token) {
//        try {
//            Jws<Claims> claims = Jwts.parserBuilder()
//                    .setSigningKey(config.getSecretKey())
//                    .build()
//                    .parseClaimsJws(token);
//            if (!"reset_password".equals(claims.getBody().get("type"))) {
//                throw new RuntimeException("Invalid token type");
//            }
//            return claims.getBody().getSubject();
//        } catch (JwtException e) {
//            throw new RuntimeException("Invalid or expired token");
//        }
//    }

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

    private boolean isValidTokenUsername(UserDetails userDetails) {
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
