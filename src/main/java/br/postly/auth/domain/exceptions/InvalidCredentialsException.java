package br.postly.auth.domain.exceptions;

import br.postly.shared.exception.WebException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends WebException {

    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}
