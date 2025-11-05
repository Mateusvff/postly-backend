package br.postly.auth.exceptions;

import br.postly.common.exception.WebException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends WebException {

    public InvalidTokenException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
