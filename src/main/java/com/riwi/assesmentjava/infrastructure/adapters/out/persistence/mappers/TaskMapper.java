package com.riwi.assesmentjava.infrastructure.adapters.out.persistence.mappers;

import com.riwi.assesmentjava.domain.model.Tasks;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.TaskEntity;

public class TaskMapper {

    public static TaskEntity toEntity(Tasks task) {
        if (task == null) {
            return null;
        }

        return TaskEntity.builder()
                .id(task.getId())
                .projectId(task.getProyectId())
                .title(task.getTitle())
                .completed(task.isCompleted())
                .deleted(task.isDeleted())
                .build();
    }

    public static Tasks toDomain(TaskEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Tasks(
                entity.getId(),
                entity.getProjectId(),
                entity.getTitle(),
                entity.isCompleted(),
                entity.isDeleted());
    }
}
