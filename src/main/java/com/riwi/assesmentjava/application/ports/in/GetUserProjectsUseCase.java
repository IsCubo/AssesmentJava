package com.riwi.assesmentjava.application.ports.in;

import com.riwi.assesmentjava.application.dto.ProjectDTO;

import java.util.List;

/**
 * Puerto de entrada para listar proyectos del usuario autenticado.
 */
public interface GetUserProjectsUseCase {

    /**
     * Obtiene todos los proyectos del usuario autenticado (no eliminados).
     * 
     * @return Lista de proyectos del usuario
     */
    List<ProjectDTO> execute();
}
