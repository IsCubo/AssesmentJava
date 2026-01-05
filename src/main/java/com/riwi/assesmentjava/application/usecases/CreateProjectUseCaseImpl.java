package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.dto.CreateProjectCommand;
import com.riwi.assesmentjava.application.dto.ProjectDTO;
import com.riwi.assesmentjava.application.ports.in.CreateProjectUseCase;
import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;

import java.util.UUID;

/**
 * Implementación del caso de uso para crear proyectos.
 */
public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;

    public CreateProjectUseCaseImpl(
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public ProjectDTO execute(CreateProjectCommand command) {
        // Obtener el usuario autenticado
        UUID currentUserId = currentUserPort.getCurrentUserId();

        // Crear el proyecto en estado DRAFT
        Proyect project = new Proyect();
        project.setId(UUID.randomUUID());
        project.setOwnerId(currentUserId);
        project.setName(command.getName());
        project.setStatus(Proyect.Status.DRAFT);
        project.setDeleted(false);

        // Guardar el proyecto
        Proyect savedProject = projectRepository.save(project);

        // Retornar DTO
        return ProjectDTO.fromDomain(savedProject);
    }
}
