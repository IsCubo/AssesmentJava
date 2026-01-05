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
@Schema(description = "Solicitud de inicio de sesión")
public class LoginRequest {
    @Schema(description = "Nombre de usuario", example = "jdoe")
    private String username;

    @Schema(description = "Contraseña", example = "secret123")
    private String password;
}
