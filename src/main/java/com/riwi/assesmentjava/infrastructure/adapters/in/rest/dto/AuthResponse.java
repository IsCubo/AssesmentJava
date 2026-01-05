package com.riwi.assesmentjava.infrastructure.adapters.in.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Respuesta de autenticación exitosa")
public class AuthResponse {
    @Schema(description = "Token JWT para autenticación Bearer", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}
