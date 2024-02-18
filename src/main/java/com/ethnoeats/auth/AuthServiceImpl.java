package com.ethnoeats.auth;

import com.ethnoeats.user.service.UserDetailsServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Override
    public String authenticateUser(String username, String password) {
        // Retrieve user details by username
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Check if the provided password matches the encoded password stored in the database
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return "USER_AUTHENTICATED";
        } else {
            return "INVALID_CREDENTIALS";
        }
    }

    @Override
    public String encodePassword(String newPassword) {
        // Encode the password using the configured password encoder
        return passwordEncoder.encode(newPassword);
    }

    @Override
    public String authenticate(String username, String password) {
        // Retrieve user details by username
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Check if the provided password matches the encoded password stored in the database
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // Generate JWT token
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                    .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
                    .compact();
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
