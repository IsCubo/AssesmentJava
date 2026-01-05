package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.dto.ProjectDTO;
import com.riwi.assesmentjava.application.ports.in.GetUserProjectsUseCase;
import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementación del caso de uso para obtener proyectos del usuario.
 */
public class GetUserProjectsUseCaseImpl implements GetUserProjectsUseCase {

    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;

    public GetUserProjectsUseCaseImpl(
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public List<ProjectDTO> execute() {
        // Obtener el usuario autenticado
        UUID currentUserId = currentUserPort.getCurrentUserId();

        // Obtener proyectos del usuario
        List<Proyect> projects = projectRepository.findByOwnerId(currentUserId);

        // Convertir a DTOs
        return projects.stream()
                .map(ProjectDTO::fromDomain)
                .collect(Collectors.toList());
    }
}
