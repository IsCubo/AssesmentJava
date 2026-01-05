package com.riwi.assesmentjava.application.ports.in;

import com.riwi.assesmentjava.application.dto.CreateTaskCommand;
import com.riwi.assesmentjava.application.dto.TaskDTO;

/**
 * Puerto de entrada para crear una nueva tarea.
 * Este caso de uso representa la acción de negocio de crear una tarea.
 */
public interface CreateTaskUseCase {

    /**
     * Crea una nueva tarea para un proyecto.
     * 
     * Reglas de negocio:
     * - El proyecto debe existir
     * - Solo el propietario del proyecto puede crear tareas
     * - La tarea se crea como no completada por defecto
     * 
     * @param command Comando con los datos de la tarea a crear
     * @return DTO de la tarea creada
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
     *                                                                                  del
     *                                                                                  proyecto
     */
    TaskDTO execute(CreateTaskCommand command);
}
