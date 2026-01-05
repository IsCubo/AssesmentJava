package com.riwi.assesmentjava.infrastructure.adapters.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Estructura estándar de errores de la API")
public class ErrorResponse {
    @Schema(description = "Mensaje detallado del error", example = "El proyecto no tiene tareas activas")
    private String message;

    @Schema(description = "Tipo de error", example = "Bad Request")
    private String error;

    @Schema(description = "Código de estado HTTP", example = "400")
    private int status;

    @Schema(description = "Fecha y hora del error")
    private LocalDateTime timestamp;
}
