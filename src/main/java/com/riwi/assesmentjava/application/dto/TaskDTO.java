package com.riwi.assesmentjava.application.dto;

import com.riwi.assesmentjava.domain.model.Tasks;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO para transferir información de tareas.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información detallada de una tarea")
public class TaskDTO {

    @Schema(description = "Identificador único de la tarea")
    private UUID id;

    @Schema(description = "Identificador del proyecto asociado")
    private UUID projectId;

    @Schema(description = "Título de la tarea", example = "Implementar login")
    private String title;

    @Schema(description = "Estado de completitud de la tarea")
    private boolean completed;

    @Schema(description = "Indica si la tarea está eliminada")
    private boolean deleted;

    /**
     * Crea un DTO desde un modelo de dominio.
     */
    public static TaskDTO fromDomain(Tasks task) {
        return TaskDTO.builder()
                .id(task.getId())
                .projectId(task.getProyectId())
                .title(task.getTitle())
                .completed(task.isCompleted())
                .deleted(task.isDeleted())
                .build();
    }
}
