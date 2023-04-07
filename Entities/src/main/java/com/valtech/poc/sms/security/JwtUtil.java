package com.valtech.poc.sms.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.valtech.poc.sms.entities.TokenBlacklist;
import com.valtech.poc.sms.repo.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

	
	@Autowired
	private UserRepo userRepo;

    private String SECRET_KEY = "secretkey";    // Define a secret key to use for JWT token creation and validation


    public String extractUsername(String token) {// Extract the username from a JWT token
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {// Extract the expiration date from a JWT token
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {// Extract a specific claim from a JWT token using a resolver function
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {// Extract all claims from a JWT token
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) { // Check if a JWT token is expired
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {// Generate a JWT token for a given user
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {// Create a JWT token with the specified claims and subject
        return Jwts.builder().setClaims(claims).setSubject(subject)//setting claims and subject in token
                .setIssuedAt(new Date(System.currentTimeMillis()))//setting issue time of token
                .setExpiration(new Date(System.currentTimeMillis() + 900000))//setting expiration time of token
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)//adding signature with algorithm and secret key
                .compact();
    }

    
 // This method validates a JWT token by checking if it matches the provided user details
    public Boolean validateToken(String token, UserDetails userDetails) {
        // Extract the username from the token
        final String username = extractUsername(token);
        
        // Check if the extracted username matches the username in the provided user details
        if (username.equals(userDetails.getUsername())) {
            // If the token is expired, throw an exception
            if (isTokenExpired(token)) {
                logger.warn("Token is expired");
                throw new RuntimeException("Token is expired");
            }
            // If the token is blacklisted, throw an exception
            if (TokenBlacklist.contains(token)) {
                logger.warn("Token is blacklisted");
                throw new RuntimeException("Token is blacklisted");
            }
            // If the token is valid, return true
            return true;
        } else {
            // If the token doesn't match the user details, return false
            return false;
        }
    }

    // This method extracts the empId claim from a JWT token
    public Integer extractEmpId(String token) {
        // Extract the empId claim from the token and convert it to an integer
        return extractClaim(token, claims -> Integer.parseInt(claims.get("empId").toString()));
    }

}
	


