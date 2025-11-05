package br.postly.auth.exceptions;

import br.postly.common.exception.WebException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends WebException {

    public InvalidCredentialsException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}
