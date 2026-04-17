package com.devhunter.shop.controller;

import com.devhunter.shop.model.User;
import com.devhunter.shop.service.AuthService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        return authService.register(
            body.get("username"),
            body.get("password"),
            body.get("email")
        );
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        User user = authService.login(
            body.get("username"),
            body.get("password")
        );
        return Map.of(
            "message", "로그인 성공",
            "userId", user.getId(),
            "username", user.getUsername()
        );
    }
}
