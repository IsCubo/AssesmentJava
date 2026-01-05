package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.dto.TaskDTO;
import com.riwi.assesmentjava.application.exception.EntityNotFoundException;
import com.riwi.assesmentjava.application.exception.UnauthorizedAccessException;
import com.riwi.assesmentjava.application.ports.in.GetProjectTasksUseCase;
import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.application.ports.out.TaskRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;
import com.riwi.assesmentjava.domain.model.Tasks;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementación del caso de uso para obtener tareas de un proyecto.
 */
public class GetProjectTasksUseCaseImpl implements GetProjectTasksUseCase {

    private final TaskRepositoryPort taskRepository;
    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;

    public GetProjectTasksUseCaseImpl(
            TaskRepositoryPort taskRepository,
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public List<TaskDTO> execute(UUID projectId) {
        // Obtener el usuario autenticado
        UUID currentUserId = currentUserPort.getCurrentUserId();

        // Verificar que el proyecto existe
        Proyect project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project", projectId));

        // Verificar que el usuario sea el propietario del proyecto
        if (!project.getOwnerId().equals(currentUserId)) {
            throw new UnauthorizedAccessException("You are not the owner of this project");
        }

        // Obtener tareas del proyecto
        List<Tasks> tasks = taskRepository.findByProjectId(projectId);

        // Convertir a DTOs
        return tasks.stream()
                .map(TaskDTO::fromDomain)
                .collect(Collectors.toList());
    }
}
