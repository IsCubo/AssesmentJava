package com.riwi.assesmentjava.application.ports.out;

import java.util.UUID;

/**
 * Puerto de salida para registrar eventos de auditoría.
 * Este puerto será implementado por un adaptador en la capa de infraestructura.
 * 
 * La implementación puede ser simple (logs) o compleja (base de datos de
 * auditoría).
 */
public interface AuditLogPort {

    /**
     * Registra una acción de auditoría.
     * 
     * @param action   Descripción de la acción realizada
     * @param entityId ID de la entidad afectada
     */
    void register(String action, UUID entityId);

    /**
     * Registra una acción de auditoría con información adicional.
     * 
     * @param action   Descripción de la acción realizada
     * @param entityId ID de la entidad afectada
     * @param userId   ID del usuario que realizó la acción
     */
    void register(String action, UUID entityId, UUID userId);
}
