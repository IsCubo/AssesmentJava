package com.riwi.assesmentjava.infrastructure.adapters.out.persistence.repositories;

import com.riwi.assesmentjava.domain.model.Proyect;
import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, UUID> {

    List<ProjectEntity> findByOwnerIdAndDeletedFalse(UUID ownerId);

    List<ProjectEntity> findByOwnerIdAndStatusAndDeletedFalse(UUID ownerId, Proyect.Status status);

    boolean existsByIdAndOwnerId(UUID id, UUID ownerId);
}
