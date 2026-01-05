package com.riwi.assesmentjava.application.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Command para crear una nueva tarea.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Comando para crear una nueva tarea")
public class CreateTaskCommand {

    @Hidden // El projectId viene en la URL, no en el body visiblemente para swagger body
            // example
    @Schema(hidden = true)
    private UUID projectId;

    @Schema(description = "Título de la tarea", example = "Implementar login")
    private String title;
}
