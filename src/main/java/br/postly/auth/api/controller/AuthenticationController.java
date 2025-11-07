package br.postly.auth.api.controller;

import br.postly.auth.api.dto.request.LoginRequest;
import br.postly.auth.api.dto.request.RegisterRequest;
import br.postly.auth.api.dto.response.AuthResponse;
import br.postly.auth.domain.service.AuthenticationService;
import br.postly.shared.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Authentication", description = "Endpoints for user registration and login")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @Operation(summary = "Register a new user", description = "Creates a new user with an email and password")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User registered", content = @Content()),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest registerRequest) {
        if (authService.isUserRegistered(registerRequest.email())) {
            return ResponseEntity.badRequest().build();
        }

        authService.registerUser(registerRequest);
        return ResponseEntity.status(CREATED).build();
    }

    @Operation(summary = "Authenticate user", description = "Authenticates with email and password and returns a JWT access token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authenticated", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Invalid credentials", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping(value = "/login", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok().body(authResponse);
    }

}
