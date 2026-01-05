package com.riwi.assesmentjava.application.ports.out;

import com.riwi.assesmentjava.domain.model.Proyect;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para operaciones de persistencia de proyectos.
 * Este puerto será implementado por un adaptador en la capa de infraestructura.
 */
public interface ProjectRepositoryPort {

    /**
     * Guarda un proyecto en el repositorio.
     * 
     * @param project Proyecto a guardar
     * @return Proyecto guardado con ID generado
     */
    Proyect save(Proyect project);

    /**
     * Busca un proyecto por su ID.
     * 
     * @param id ID del proyecto
     * @return Optional con el proyecto si existe y no está eliminado
     */
    Optional<Proyect> findById(UUID id);

    /**
     * Busca todos los proyectos de un usuario (no eliminados).
     * 
     * @param ownerId ID del propietario
     * @return Lista de proyectos del usuario
     */
    List<Proyect> findByOwnerId(UUID ownerId);

    /**
     * Busca todos los proyectos activos de un usuario (no eliminados).
     * 
     * @param ownerId ID del propietario
     * @return Lista de proyectos activos del usuario
     */
    List<Proyect> findActiveByOwnerId(UUID ownerId);

    /**
     * Verifica si un proyecto existe y pertenece al usuario dado.
     * 
     * @param projectId ID del proyecto
     * @param ownerId   ID del propietario
     * @return true si el proyecto existe y pertenece al usuario
     */
    boolean existsByIdAndOwnerId(UUID projectId, UUID ownerId);
}
