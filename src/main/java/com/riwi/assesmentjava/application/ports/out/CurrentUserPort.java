package com.riwi.assesmentjava.application.ports.out;

import java.util.UUID;

/**
 * Puerto de salida para obtener información del usuario autenticado actual.
 * Este puerto será implementado por un adaptador en la capa de infraestructura
 * que interactúa con Spring Security.
 */
public interface CurrentUserPort {

    /**
     * Obtiene el ID del usuario autenticado actualmente.
     * 
     * @return UUID del usuario autenticado
     * @throws RuntimeException si no hay usuario autenticado
     */
    UUID getCurrentUserId();

    /**
     * Obtiene el username del usuario autenticado actualmente.
     * 
     * @return Username del usuario autenticado
     * @throws RuntimeException si no hay usuario autenticado
     */
    String getCurrentUsername();
}
