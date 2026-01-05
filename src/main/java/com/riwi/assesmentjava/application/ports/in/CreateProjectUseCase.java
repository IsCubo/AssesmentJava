package com.riwi.assesmentjava.application.ports.in;

import com.riwi.assesmentjava.application.dto.CreateProjectCommand;
import com.riwi.assesmentjava.application.dto.ProjectDTO;

/**
 * Puerto de entrada para crear un nuevo proyecto.
 * Este caso de uso representa la acción de negocio de crear un proyecto.
 */
public interface CreateProjectUseCase {

    /**
     * Crea un nuevo proyecto para el usuario autenticado.
     * El proyecto se crea en estado DRAFT por defecto.
     * 
     * @param command Comando con los datos del proyecto a crear
     * @return DTO del proyecto creado
     */
    ProjectDTO execute(CreateProjectCommand command);
}
