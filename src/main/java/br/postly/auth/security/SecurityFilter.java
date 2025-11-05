package br.postly.auth.security;

import br.postly.auth.exceptions.InvalidTokenException;
import br.postly.auth.service.AuthenticationService;
import br.postly.auth.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AuthenticationService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        authenticateUser(request);
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(HttpServletRequest request) {
        String accessToken = extractAccessToken(request);

        String email = tokenService.validateToken(accessToken);
        UserDetails user = authService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(createAuthentication(user));
    }

    private UsernamePasswordAuthenticationToken createAuthentication(UserDetails user) {
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    private String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException("Invalid token");
        }

        return authorizationHeader.substring(7);
    }

}
