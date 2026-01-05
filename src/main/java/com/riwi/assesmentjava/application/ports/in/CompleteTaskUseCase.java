package com.riwi.assesmentjava.application.ports.in;

import java.util.UUID;

/**
 * Puerto de entrada para completar una tarea.
 * Este caso de uso representa la acción de negocio de completar una tarea.
 */
public interface CompleteTaskUseCase {

    /**
     * Marca una tarea como completada.
     * 
     * Reglas de negocio:
     * - La tarea debe existir
     * - Solo el propietario del proyecto puede completar la tarea
     * - Una tarea completada no puede modificarse
     * - Se debe generar auditoría
     * - Se debe generar notificación
     * 
     * @param taskId ID de la tarea a completar
     * @throws com.riwi.assesmentjava.application.exception.EntityNotFoundException     si
     *                                                                                  la
     *                                                                                  tarea
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
     *                                                                                  la
     *                                                                                  tarea
     *                                                                                  ya
     *                                                                                  está
     *                                                                                  completada
     */
    void execute(UUID taskId);
}
