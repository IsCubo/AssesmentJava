package com.riwi.assesmentjava.infrastructure.adapters.out.persistence.adapters;

import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.ProjectEntity;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.mappers.ProjectMapper;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.repositories.ProjectJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProjectRepositoryAdapter implements ProjectRepositoryPort {

    private final ProjectJpaRepository projectJpaRepository;

    public ProjectRepositoryAdapter(ProjectJpaRepository projectJpaRepository) {
        this.projectJpaRepository = projectJpaRepository;
    }

    @Override
    public Proyect save(Proyect project) {
        ProjectEntity entity = ProjectMapper.toEntity(project);
        ProjectEntity savedEntity = projectJpaRepository.save(entity);
        return ProjectMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Proyect> findById(UUID id) {
        return projectJpaRepository.findById(id)
                .filter(p -> !p.isDeleted()) // Filtrar si está eliminado lógicamente, aunque el repositorio podría
                                             // filtrar también
                .map(ProjectMapper::toDomain);
    }

    @Override
    public List<Proyect> findByOwnerId(UUID ownerId) {
        return projectJpaRepository.findByOwnerIdAndDeletedFalse(ownerId).stream()
                .map(ProjectMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Proyect> findActiveByOwnerId(UUID ownerId) {
        return projectJpaRepository.findByOwnerIdAndStatusAndDeletedFalse(ownerId, Proyect.Status.ACTIVE).stream()
                .map(ProjectMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByIdAndOwnerId(UUID projectId, UUID ownerId) {
        return projectJpaRepository.existsByIdAndOwnerId(projectId, ownerId);
    }
}
