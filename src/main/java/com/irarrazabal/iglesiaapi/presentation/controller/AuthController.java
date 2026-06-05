package com.irarrazabal.iglesiaapi.presentation.controller;

import com.irarrazabal.iglesiaapi.application.dashboard.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.auth.dto.AuthResponse;
import com.irarrazabal.iglesiaapi.application.auth.dto.LoginRequest;
import com.irarrazabal.iglesiaapi.application.auth.dto.RegisterRequest;
import com.irarrazabal.iglesiaapi.application.auth.AuthService.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

   private AuthService authService;

   public AuthController(AuthService authService) {
       this.authService = authService;
   }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest request) {

        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Usuario creado con exito"));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

       return ResponseEntity.ok((authService.login(request)));

    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Solo admins";
    }
}
