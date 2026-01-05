package com.riwi.assesmentjava.infrastructure.adapters.out.persistence.adapters;

import com.riwi.assesmentjava.application.ports.out.TaskRepositoryPort;
import com.riwi.assesmentjava.domain.model.Tasks;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.TaskEntity;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.mappers.TaskMapper;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.repositories.TaskJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryAdapter(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Tasks save(Tasks task) {
        TaskEntity entity = TaskMapper.toEntity(task);
        TaskEntity savedEntity = taskJpaRepository.save(entity);
        return TaskMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Tasks> findById(UUID id) {
        return taskJpaRepository.findById(id)
                .filter(t -> !t.isDeleted())
                .map(TaskMapper::toDomain);
    }

    @Override
    public List<Tasks> findByProjectId(UUID projectId) {
        return taskJpaRepository.findByProjectIdAndDeletedFalse(projectId).stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countActiveTasksByProjectId(UUID projectId) {
        return taskJpaRepository.countByProjectIdAndCompletedFalseAndDeletedFalse(projectId);
    }

    @Override
    public List<Tasks> findActiveByProjectId(UUID projectId) {
        return taskJpaRepository.findByProjectIdAndCompletedFalseAndDeletedFalse(projectId).stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }
}
