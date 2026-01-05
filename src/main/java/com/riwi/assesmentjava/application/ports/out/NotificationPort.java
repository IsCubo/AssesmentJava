package com.riwi.assesmentjava.application.ports.out;

/**
 * Puerto de salida para enviar notificaciones.
 * Este puerto será implementado por un adaptador en la capa de infraestructura.
 * 
 * La implementación puede ser simple (logs) o compleja (email, SMS, push
 * notifications).
 */
public interface NotificationPort {

    /**
     * Envía una notificación con un mensaje.
     * 
     * @param message Mensaje de la notificación
     */
    void notify(String message);

    /**
     * Envía una notificación a un usuario específico.
     * 
     * @param userId  ID del usuario destinatario
     * @param message Mensaje de la notificación
     */
    void notifyUser(String userId, String message);
}
