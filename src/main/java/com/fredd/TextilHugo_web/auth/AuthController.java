package com.fredd.TextilHugo_web.auth;

import com.fredd.TextilHugo_web.exceptions.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            tags = {"Autenticacion"},
            operationId = "authLogin",
            summary = "Iniciar sesion",
            description = "Iniciar sesion",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de inicio de sesion"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Sesion autorizada", content = @Content(schema = @Schema(implementation = LoginResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Credenciales incorrectas", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = "{ \"estado\": \"Bad credentials\", \"mensaje\": \"uri=/api/v1/auth/login\" }")
                    ))
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(
            tags = {"Autenticacion"},
            operationId = "authRegister",
            summary = "Registrarse",
            description = "Registrarse como cliente nuevo",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de registro"
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente registrado", content = @Content(schema = @Schema(implementation = RegisterResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Username o Email ya en uso", content = @Content(
                            schema = @Schema(implementation = BadRequestException.class),
                            examples = @ExampleObject(value = "{ \"estado\": \"Username already exists\", \"mensaje\": \"uri=/api/v1/auth/register\" }")
                    ))            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }

}
