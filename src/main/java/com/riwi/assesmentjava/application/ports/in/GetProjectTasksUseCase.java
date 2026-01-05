package com.riwi.assesmentjava.application.ports.in;

import com.riwi.assesmentjava.application.dto.TaskDTO;

import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada para listar tareas de un proyecto.
 */
public interface GetProjectTasksUseCase {

    /**
     * Obtiene todas las tareas de un proyecto (no eliminadas).
     * Solo el propietario del proyecto puede ver sus tareas.
     * 
     * @param projectId ID del proyecto
     * @return Lista de tareas del proyecto
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
     */
    List<TaskDTO> execute(UUID projectId);
}
