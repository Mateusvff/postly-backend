package br.postly.shared.dto;

import java.time.Instant;

public record ErrorResponse(Instant instant, String message) {}
