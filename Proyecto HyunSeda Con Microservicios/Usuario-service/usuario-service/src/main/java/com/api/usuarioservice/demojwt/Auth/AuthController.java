package com.api.usuarioservice.demojwt.Auth;

import com.api.usuarioservice.exceptions.UserDomainException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "login")
    @Operation(summary = "Iniciar sesión",
            description = "Permite a un usuario iniciar sesión proporcionando sus credenciales.")
    @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class),
                    examples = @ExampleObject(value = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\"}")))
    @ApiResponse(responseCode = "401", description = "Credenciales incorrectas",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"UNAUTHORIZED\", \"message\": \"Credenciales incorrectas\"}]}")))
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping(value = "register")
    @Operation(summary = "Registrar un nuevo usuario",
            description = "Permite registrar un nuevo usuario proporcionando los datos requeridos.")
    @ApiResponse(responseCode = "200", description = "Registro exitoso",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class),
                    examples = @ExampleObject(value = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\"}")))
    @ApiResponse(responseCode = "422", description = "Datos de registro inválidos",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"INVALID_DATA\", \"message\": \"Los datos de registro son inválidos\"}]}")))
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) throws UserDomainException {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
