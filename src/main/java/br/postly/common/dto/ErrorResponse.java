package br.postly.common.dto;

import java.time.Instant;

public record ErrorResponse(Instant instant, String message) {}
