
package com.codemiser.e_commerce_app.controllers;

import com.codemiser.e_commerce_app.dto.LoginRequest;
import com.codemiser.e_commerce_app.dto.LoginResponse;
import com.codemiser.e_commerce_app.service.AuthService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return new LoginResponse(token);
    }
}
