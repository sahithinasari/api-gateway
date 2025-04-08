package com.skinzen.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Component
public class JwtUtil {

    private String secret = "mySecretKey"; // Keep this secret in application properties or env
    private SecretKey secretKey;
    @PostConstruct
    public void init() {
        this.secretKey = generateSecureKey(secret);
    }

    // ✅ Method to generate a secure 256-bit key from a short secret
    private SecretKey generateSecureKey(String shortSecret) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha.digest(shortSecret.getBytes(StandardCharsets.UTF_8));

            return Keys.hmacShaKeyFor(keyBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate secure key", e);
        }
    }

    @Bean
    public SecretKey getSecretKey() {
        return secretKey;
    }


    public void validateToken(String token) throws Exception {
        extractClaims(token);
    }
    // ✅ Extract Claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // ✅ Extract Expiration Date
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

}
