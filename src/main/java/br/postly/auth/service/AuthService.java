package br.postly.auth.service;

import br.postly.auth.enums.Role;
import br.postly.auth.model.User;
import br.postly.auth.repository.UserRepository;
import br.postly.common.exception.WebException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("User not found with email: " + email);

        return user;
    }

    public boolean isUserRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void saveUser(String email, String encryptedPassword) {
        try {
            User user = new User(email, encryptedPassword, Role.USER);
            userRepository.save(user);
        } catch (Exception e) {
            throw new WebException("Error saving user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
