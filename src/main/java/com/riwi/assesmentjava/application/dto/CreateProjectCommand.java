package com.riwi.assesmentjava.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Command para crear un nuevo proyecto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Comando para crear un nuevo proyecto")
public class CreateProjectCommand {

    @Schema(description = "Nombre del proyecto", example = "Nuevo Proyecto de Software")
    private String name;
}
