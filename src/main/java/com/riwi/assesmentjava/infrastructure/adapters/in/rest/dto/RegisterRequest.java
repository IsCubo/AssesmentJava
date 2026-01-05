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
@Schema(description = "Solicitud de registro de nuevo usuario")
public class RegisterRequest {
    @Schema(description = "Nombre de usuario único", example = "jdoe")
    private String username;

    @Schema(description = "Correo electrónico", example = "jdoe@example.com")
    private String email;

    @Schema(description = "Contraseña segura", example = "secret123")
    private String password;
}
