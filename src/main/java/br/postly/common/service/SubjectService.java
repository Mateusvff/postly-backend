package br.postly.common.service;

import br.postly.auth.model.User;
import br.postly.common.exception.SubjectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SubjectService {

    public User getCurrentUser() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            log.error("Error getting current user: {}", e.getMessage());
            throw new SubjectException("Error getting current user: " + e.getMessage());
        }
    }

}
