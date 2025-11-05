package br.postly.auth.exceptions;

import br.postly.common.exception.WebException;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends WebException {

    public AuthorizationException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
