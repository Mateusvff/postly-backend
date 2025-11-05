package br.postly.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WebException extends RuntimeException {

    private final HttpStatus httpStatus;

    public WebException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
