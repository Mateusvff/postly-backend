package br.postly.shared.exception;

import br.postly.shared.dto.ErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(WebException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(WebException ex) {
        ErrorResponse errorResponse = new ErrorResponse(Instant.now(), ex.getMessage());
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
