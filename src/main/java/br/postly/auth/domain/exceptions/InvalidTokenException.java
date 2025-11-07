package br.postly.auth.domain.exceptions;

import br.postly.shared.exception.WebException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends WebException {

    public InvalidTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
