package com.example.codeengine.expense.middleware;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javassist.tools.web.BadHttpRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY_STRING = "chintan_sharma_chintan_sharma_chintan_sharma_chintan_sharma_chintan_sharma_chintan_sharma";

    private static final SecretKey SECRET_KEY = convertStringToKey(SECRET_KEY_STRING);

    public static SecretKey convertStringToKey(String keyString) {
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256");
    }

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) throws SecurityException {
        String jwtToken = extractToken(token);
        if (!validateToken(jwtToken)) throw new SecurityException("Authorization Error!");
        String username = extractClaims(jwtToken).getSubject();
        return username;
    }

    public boolean validateToken(String token) {
        boolean result = !extractClaims(token).getExpiration().before(new Date());
        return result;
    }

    public String extractToken(String authorizationHeader) {
        // Assuming the token is prefixed with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        // If the header doesn't follow the expected format, handle it accordingly
        return null;
    }

    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
