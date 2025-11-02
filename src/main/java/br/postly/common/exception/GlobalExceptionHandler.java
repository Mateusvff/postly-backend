package br.postly.common.exception;

import br.postly.common.dto.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(WebException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(WebException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(Instant.now(), ex.getMessage()));
    }
}
