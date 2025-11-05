package br.postly.auth.service;

import br.postly.auth.dto.request.LoginRequest;
import br.postly.auth.dto.request.RegisterRequest;
import br.postly.auth.dto.response.AuthResponse;
import br.postly.auth.enums.Role;
import br.postly.auth.exceptions.InvalidCredentialsException;
import br.postly.auth.model.User;
import br.postly.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
            var auth = authenticationManager.authenticate(usernamePassword);

            User user = (User) auth.getPrincipal();

            String token = tokenService.generateToken(user);
            return new AuthResponse(token);
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

    }

    public boolean isUserRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void registerUser(RegisterRequest registerInfo) {
        String encryptedPassword = passwordEncoder.encode(registerInfo.password());

        User user = new User(registerInfo.email(), encryptedPassword, Role.USER);
        userRepository.save(user);
    }

}
