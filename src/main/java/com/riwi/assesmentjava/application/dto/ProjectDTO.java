package com.riwi.assesmentjava.application.dto;

import com.riwi.assesmentjava.domain.model.Proyect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO para transferir información de proyectos.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información detallada de un proyecto")
public class ProjectDTO {

    @Schema(description = "Identificador único del proyecto")
    private UUID id;

    @Schema(description = "Identificador del usuario propietario")
    private UUID ownerId;

    @Schema(description = "Nombre del proyecto", example = "Proyecto de Software")
    private String name;

    @Schema(description = "Estado actual del proyecto", example = "DRAFT")
    private Proyect.Status status;

    @Schema(description = "Indica si el proyecto está eliminado")
    private boolean deleted;

    /**
     * Crea un DTO desde un modelo de dominio.
     */
    public static ProjectDTO fromDomain(Proyect project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .ownerId(project.getOwnerId())
                .name(project.getName())
                .status(project.getStatus())
                .deleted(project.isDeleted())
                .build();
    }
}
