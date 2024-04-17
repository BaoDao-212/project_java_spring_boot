package com.amigoscode.config.auth;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.amigoscode.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String SECRET_KEY="z39nXajhdbWcLkfcHys/rq8p0d/DV0akK0+On1bNxXOM7dEdpGjof6K/JsL7V6U";
    public String extractUsername(String jwt) {
        return extractClaim(jwt,Claims::getSubject);
    }
    public <T> T extractClaim(String jwt, Function<Claims,T> claimsResolver) {
        // Extract claim from JWT
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }
    public String generateToken(User userDetails) {
        // Generate JWT
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken( Map<String, Object> extraClaims, User userDetails) {
        // Generate JWT 
        return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
        .signWith(getSignInKey(),SignatureAlgorithm.HS256).compact();
    }
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        // Check if JWT is valid
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    private Claims extractAllClaims(String token) {
        // Extract all claims from JWT
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        // Get signing key
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }

    public Boolean validateToken(String token) {
        // Validate JWT
        return false;
    }
   
    private Boolean isTokenExpired(String token) {
        // Check if JWT is expired
        return extractExpriryDate(token).before(new Date()) ;
    }
    private Date extractExpriryDate(String token) {
        // Extract expiration date from JWT
        return extractClaim(token,Claims::getExpiration);
    }
}