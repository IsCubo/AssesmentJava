package com.riwi.assesmentjava.infrastructure.adapters.out.persistence.mappers;

import com.riwi.assesmentjava.domain.model.Proyect;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.ProjectEntity;

public class ProjectMapper {

    public static ProjectEntity toEntity(Proyect project) {
        if (project == null) {
            return null;
        }

        return ProjectEntity.builder()
                .id(project.getId())
                .ownerId(project.getOwnerId())
                .name(project.getName())
                .status(project.getStatus())
                .deleted(project.isDeleted())
                .build();
    }

    public static Proyect toDomain(ProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Proyect(
                entity.getId(),
                entity.getOwnerId(),
                entity.getName(),
                entity.getStatus(),
                entity.isDeleted());
    }
}
