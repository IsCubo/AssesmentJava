package com.riwi.assesmentjava.application.ports.out;

import com.riwi.assesmentjava.domain.model.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para operaciones de persistencia de usuarios.
 * Este puerto será implementado por un adaptador en la capa de infraestructura.
 */
public interface UserRepositoryPort {

    /**
     * Guarda un usuario en el repositorio.
     * 
     * @param user Usuario a guardar
     * @return Usuario guardado con ID generado
     */
    User save(User user);

    /**
     * Busca un usuario por su ID.
     * 
     * @param id ID del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findById(UUID id);

    /**
     * Busca un usuario por su username.
     * 
     * @param username Username del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por su email.
     * 
     * @param email Email del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica si existe un usuario con el username dado.
     * 
     * @param username Username a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByUsername(String username);

    /**
     * Verifica si existe un usuario con el email dado.
     * 
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);
}
