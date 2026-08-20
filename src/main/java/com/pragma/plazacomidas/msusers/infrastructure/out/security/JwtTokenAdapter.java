package com.pragma.plazacomidas.msusers.infrastructure.out.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import com.pragma.plazacomidas.msusers.domain.spi.ITokenPort;

public class JwtTokenAdapter implements ITokenPort {


    private final Key signingKey;
    private final long expirationTimeInMillis;

    public JwtTokenAdapter(String secret, long expirationTimeInMillis) {
        this.expirationTimeInMillis = expirationTimeInMillis;
        this.signingKey = buildKey(secret);
    }

    private Key buildKey(String secret) {  
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);      
        //byte[] keyBytes = Decoders.BASE64.decode(secret); <- si la clave ya está cifrada en base64
        return Keys.hmacShaKeyFor(keyBytes);        
    }

    @Override
    public String generateToken(Long id, String email, String role, Long restaurantId) {
        long nowMillis = System.currentTimeMillis();
        Date issuedAt = new Date(nowMillis);
        Date expiration = new Date(nowMillis + expirationTimeInMillis);

        return Jwts.builder()
                // 1. Añadimos los claims (lo que viene por arguemento)
                .claim("id", id)
                .claim("email", email)
                .claim("role", role)
                .claim("restaurantId", restaurantId)
                
                // 2. Cuándo se emitió
                .setIssuedAt(issuedAt)
                
                // 3. Cuándo expira
                .setExpiration(expiration)
                
                // 4. Se firma con la clave y el algoritmo
                .signWith(signingKey, SignatureAlgorithm.HS256)
                
                // 5. Devuelve el String compacto
                .compact();

                
    }
    
    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    public Long extractId(String token) {
        return extractAllClaims(token).get("id", Long.class);
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; //si ya expiró
        }
    }

    //Método auxiliar para leer el payload del JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

