package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.dto.CreateTaskCommand;
import com.riwi.assesmentjava.application.dto.TaskDTO;
import com.riwi.assesmentjava.application.exception.EntityNotFoundException;
import com.riwi.assesmentjava.application.exception.UnauthorizedAccessException;
import com.riwi.assesmentjava.application.ports.in.CreateTaskUseCase;
import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.application.ports.out.TaskRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;
import com.riwi.assesmentjava.domain.model.Tasks;

import java.util.UUID;

/**
 * Implementación del caso de uso para crear tareas.
 * 
 * Reglas de negocio:
 * - El proyecto debe existir
 * - Solo el propietario del proyecto puede crear tareas
 */
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepository;
    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;

    public CreateTaskUseCaseImpl(
            TaskRepositoryPort taskRepository,
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public TaskDTO execute(CreateTaskCommand command) {
        // Obtener el usuario autenticado
        UUID currentUserId = currentUserPort.getCurrentUserId();

        // Verificar que el proyecto existe
        Proyect project = projectRepository.findById(command.getProjectId())
                .orElseThrow(() -> new EntityNotFoundException("Project", command.getProjectId()));

        // Verificar que el usuario sea el propietario del proyecto
        if (!project.getOwnerId().equals(currentUserId)) {
            throw new UnauthorizedAccessException("You are not the owner of this project");
        }

        // Crear la tarea
        Tasks task = new Tasks();
        task.setId(UUID.randomUUID());
        task.setProyectId(command.getProjectId());
        task.setTitle(command.getTitle());
        task.setCompleted(false);
        task.setDeleted(false);

        // Guardar la tarea
        Tasks savedTask = taskRepository.save(task);

        // Retornar DTO
        return TaskDTO.fromDomain(savedTask);
    }
}
