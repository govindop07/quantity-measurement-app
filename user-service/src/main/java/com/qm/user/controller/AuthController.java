package com.qm.user.controller;

import com.qm.user.dto.AuthResponse;
import com.qm.user.dto.GoogleAuthRequest;
import com.qm.user.dto.LoginRequest;
import com.qm.user.dto.RegisterRequest;
import com.qm.user.entity.UserEntity;
import com.qm.user.repository.UserRepository;
import com.qm.user.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthProvider("LOCAL");
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElse(null);
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleAuthRequest request) {
        try {
            // Verify Google ID token using Google's tokeninfo endpoint
            String tokenInfoUrl = "https://oauth2.googleapis.com/tokeninfo?id_token=" + request.getCredential();
            RestTemplate restTemplate = new RestTemplate();
            @SuppressWarnings("unchecked")
            Map<String, Object> tokenInfo = restTemplate.getForObject(tokenInfoUrl, Map.class);

            if (tokenInfo == null || tokenInfo.get("email") == null) {
                return ResponseEntity.status(401).body("Invalid Google token");
            }

            String email = (String) tokenInfo.get("email");
            String name = (String) tokenInfo.get("name");
            if (name == null || name.isEmpty()) {
                name = email.split("@")[0]; // fallback to email prefix
            }

            // Find existing user or create new one
            UserEntity user = userRepository.findByEmail(email).orElse(null);

            if (user == null) {
                // Auto-register Google user
                user = new UserEntity();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(passwordEncoder.encode(java.util.UUID.randomUUID().toString()));
                user.setAuthProvider("GOOGLE");
                userRepository.save(user);
            }

            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Google authentication failed: " + e.getMessage());
        }
    }
}
