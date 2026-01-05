package com.riwi.assesmentjava.application.ports.in;

import java.util.UUID;

/**
 * Puerto de entrada para activar un proyecto.
 * Este caso de uso representa la acción de negocio de activar un proyecto.
 */
public interface ActivateProjectUseCase {

    /**
     * Activa un proyecto cambiando su estado de DRAFT a ACTIVE.
     * 
     * Reglas de negocio:
     * - El proyecto debe tener al menos una tarea activa (no completada, no
     * eliminada)
     * - Solo el propietario del proyecto puede activarlo
     * - Se debe generar auditoría
     * - Se debe generar notificación
     * 
     * @param projectId ID del proyecto a activar
     * @throws com.riwi.assesmentjava.application.exception.EntityNotFoundException     si
     *                                                                                  el
     *                                                                                  proyecto
     *                                                                                  no
     *                                                                                  existe
     * @throws com.riwi.assesmentjava.application.exception.UnauthorizedAccessException si
     *                                                                                  el
     *                                                                                  usuario
     *                                                                                  no
     *                                                                                  es
     *                                                                                  el
     *                                                                                  propietario
     * @throws com.riwi.assesmentjava.application.exception.BusinessRuleException       si
     *                                                                                  el
     *                                                                                  proyecto
     *                                                                                  no
     *                                                                                  tiene
     *                                                                                  tareas
     *                                                                                  activas
     */
    void execute(UUID projectId);
}
