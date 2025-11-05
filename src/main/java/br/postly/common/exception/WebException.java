package br.postly.common.exception;

import lombok.Getter;

@Getter
public class WebException extends RuntimeException {

    public WebException(String message) {
        super(message);
    }
}
