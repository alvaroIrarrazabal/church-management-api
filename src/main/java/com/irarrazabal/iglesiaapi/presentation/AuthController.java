package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.dto.auth.AuthResponse;
import com.irarrazabal.iglesiaapi.application.dto.auth.ChangeRoleRequest;
import com.irarrazabal.iglesiaapi.application.dto.auth.LoginRequest;
import com.irarrazabal.iglesiaapi.application.dto.auth.RegisterRequest;
import com.irarrazabal.iglesiaapi.application.service.AuthService;
import jakarta.validation.Valid;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> register(@RequestBody @Valid RegisterRequest request) {

        authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MessageResponse("Usuario creado con exito"));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

       return ResponseEntity.ok((authService.login(request)));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Solo admins";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}/role")
    public ResponseEntity<MessageResponse> changeRol(@PathVariable Long id, @RequestBody ChangeRoleRequest request){
       authService.changeRole(id, request);

       return ResponseEntity.ok(new MessageResponse("Rol actualizado correctamente, ahora tu rol es :"+request.rol().name()));
    }

}
