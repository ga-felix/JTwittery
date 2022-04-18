package org.monitordigital.jtwittery.service.dto;

import java.time.Instant;

public record ExceptionDto(Long code,
                           String message,
                           Instant timestamp) {
}