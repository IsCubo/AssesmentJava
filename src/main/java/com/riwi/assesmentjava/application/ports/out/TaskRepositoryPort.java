package com.riwi.assesmentjava.application.ports.out;

import com.riwi.assesmentjava.domain.model.Tasks;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para operaciones de persistencia de tareas.
 * Este puerto será implementado por un adaptador en la capa de infraestructura.
 */
public interface TaskRepositoryPort {

    /**
     * Guarda una tarea en el repositorio.
     * 
     * @param task Tarea a guardar
     * @return Tarea guardada con ID generado
     */
    Tasks save(Tasks task);

    /**
     * Busca una tarea por su ID.
     * 
     * @param id ID de la tarea
     * @return Optional con la tarea si existe y no está eliminada
     */
    Optional<Tasks> findById(UUID id);

    /**
     * Busca todas las tareas de un proyecto (no eliminadas).
     * 
     * @param projectId ID del proyecto
     * @return Lista de tareas del proyecto
     */
    List<Tasks> findByProjectId(UUID projectId);

    /**
     * Cuenta las tareas activas (no completadas y no eliminadas) de un proyecto.
     * 
     * @param projectId ID del proyecto
     * @return Cantidad de tareas activas
     */
    long countActiveTasksByProjectId(UUID projectId);

    /**
     * Busca todas las tareas activas (no completadas y no eliminadas) de un
     * proyecto.
     * 
     * @param projectId ID del proyecto
     * @return Lista de tareas activas
     */
    List<Tasks> findActiveByProjectId(UUID projectId);
}
