package com.bank.portal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TestController {
    
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping("/hash/{password}")
    public String generateHash(@PathVariable String password) {
        return passwordEncoder.encode(password);
    }
    
    @GetMapping("/verify")
    public String verifyPassword(@RequestParam String raw, @RequestParam String hash) {
        boolean matches = passwordEncoder.matches(raw, hash);
        return "Matches: " + matches;
    }
}
