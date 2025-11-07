package br.postly.shared.exception;

import org.springframework.http.HttpStatus;

public class SubjectException extends WebException {

    public SubjectException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
