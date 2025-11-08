package br.postly.shared.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends WebException {

    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
