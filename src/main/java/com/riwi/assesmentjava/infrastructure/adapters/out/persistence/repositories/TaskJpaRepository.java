package com.riwi.assesmentjava.infrastructure.adapters.out.persistence.repositories;

import com.riwi.assesmentjava.infrastructure.adapters.out.persistence.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {

    List<TaskEntity> findByProjectIdAndDeletedFalse(UUID projectId);

    List<TaskEntity> findByProjectIdAndCompletedFalseAndDeletedFalse(UUID projectId);

    long countByProjectIdAndCompletedFalseAndDeletedFalse(UUID projectId);
}
