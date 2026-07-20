package com.irarrazabal.iglesiaapi.presentation;

import com.irarrazabal.iglesiaapi.application.dto.MessageResponse;
import com.irarrazabal.iglesiaapi.application.dto.auth.*;
import com.irarrazabal.iglesiaapi.application.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CurrentUserResponse> me(){

       return  ResponseEntity.ok(authService.me());
    }

    @PutMapping("/change-password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MessageResponse> changePassword(@AuthenticationPrincipal UserDetails userDetails,
                                                         @Valid @RequestBody ChangePasswordRequest request){

       authService.changePassword(userDetails.getUsername(),request);

       return ResponseEntity.ok(
               new MessageResponse("Contraseña actualizada correctamente")
       );
    }

    @GetMapping("/verify")
    public ResponseEntity<MessageResponse> verifyAccount(
            @RequestParam String token) {

        authService.verifyAccount(token);

        return ResponseEntity.ok(
                new MessageResponse("Cuenta verificada correctamente.")
        );
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request){

        authService.resetPassword(request);

        return ResponseEntity.ok(
                new MessageResponse(
                        "Contraseña actualizada correctamente."
                )
        );

    }

    @PostMapping("/resend-verication")
    public ResponseEntity<MessageResponse>resendVerification(@Valid @RequestBody ResendVerificationRequest request){
       authService.resendVerificationEmail(request);
       return  ResponseEntity.ok(new MessageResponse("Si el correo existe y la cuenta aún no está verificada, recibirás un nuevo correo de verificación."));

    }




}
