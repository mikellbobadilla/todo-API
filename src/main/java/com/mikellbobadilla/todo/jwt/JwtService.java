package com.mikellbobadilla.todo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = getSubject(token);
        final Date expiration = getAllClaims(token).getExpiration();
        return (username.equals(userDetails.getUsername()) && !expiration.before(new Date()));
    }

    public String getSubject(String token){
        return getAllClaims(token).getSubject();
    }

    public Claims getAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String createToken(UserDetails userDetails){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(getSignInKey())
                .compact();
    }

    private Key getSignInKey(){
        String secretKey = "TF4f0sK4HEoOuDCeklOqiaKwXgqxQXnDRju3Y+tlUWVODtUqCa80W1WkFlXnsXrv5Qq0hMAuf4vYW83GEZDuA3QJ5kANQEKHqYAwi0ZHiMpjgfNMILm3E7sD04vZq/HS2JIifyDNfZq7CVtvgZdtOgOkXbj1gAAYfMP0+kSL4oIoxpwXsdEUIcwMPJHstp+AN1HCM8EGPR0aMoxF4nQeYOZxo2XeHlbe/q+pteS+8tSrqSK6Hw0pfJnzuhA4ubxNpgYc6GFiVaJHa53ckBhSZBc1upOocdmKUP7DvTURh6SIsi8fy/EsjSAisq2ikNqgdlOYaejKY+cvNODA62u4";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
