package com.tcs.controller;

import com.tcs.model.*;
import com.tcs.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        String token =
                jwtUtil.generateToken(request.getUsername());

        return new AuthResponse(token);
    }
}