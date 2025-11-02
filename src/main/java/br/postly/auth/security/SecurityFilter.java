package br.postly.auth.security;

import br.postly.auth.service.AuthService;
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
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        authenticateUser(request);
        filterChain.doFilter(request, response);
    }

    private void authenticateUser(HttpServletRequest request) {
        Optional.ofNullable(recoverToken(request))
                .ifPresent(token -> {
                    String subject = tokenService.validateToken(token);
                    if (!subject.isEmpty()) {
                        UserDetails user = authService.loadUserByUsername(subject);
                        var authentication = createAuthentication(user);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                });
    }

    private UsernamePasswordAuthenticationToken createAuthentication(UserDetails user) {
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    private String recoverToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(header -> !header.isBlank() && header.startsWith("Bearer"))
                .map(header -> header.substring("Bearer".length()))
                .orElse(null);
    }

}
