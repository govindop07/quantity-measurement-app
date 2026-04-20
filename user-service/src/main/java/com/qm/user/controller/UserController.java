package com.qm.user.controller;

import com.qm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Internal controller for service-to-service communication.
 * Called by measurement-service via Feign to validate user existence.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Validates if a user exists by email.
     * This is an internal endpoint — no JWT required (permitted in SecurityConfig).
     */
    @GetMapping("/validate")
    public boolean validateUser(@RequestParam("email") String email) {
        return userRepository.existsByEmail(email);
    }
}
