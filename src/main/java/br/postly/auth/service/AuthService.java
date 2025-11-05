package br.postly.auth.service;

import br.postly.auth.dto.request.LoginRequest;
import br.postly.auth.dto.request.RegisterRequest;
import br.postly.auth.dto.response.AuthResponse;
import br.postly.auth.enums.Role;
import br.postly.auth.model.User;
import br.postly.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public AuthResponse authenticateUser(LoginRequest loginRequest) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();

        String token = tokenService.generateToken(user);
        return new AuthResponse(token);
    }

    public boolean isUserRegistered(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void registerUser(RegisterRequest registerInfo) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerInfo.password());

        User user = new User(registerInfo.email(), encryptedPassword, Role.USER);
        userRepository.save(user);
    }

}
