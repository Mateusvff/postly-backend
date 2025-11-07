package br.postly.auth.domain.exceptions;

import br.postly.shared.exception.WebException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends WebException {

    public AuthorizationException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
