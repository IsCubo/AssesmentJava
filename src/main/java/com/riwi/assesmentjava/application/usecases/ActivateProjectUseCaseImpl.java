package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.exception.BusinessRuleException;
import com.riwi.assesmentjava.application.exception.EntityNotFoundException;
import com.riwi.assesmentjava.application.exception.UnauthorizedAccessException;
import com.riwi.assesmentjava.application.ports.in.ActivateProjectUseCase;
import com.riwi.assesmentjava.application.ports.out.AuditLogPort;
import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.application.ports.out.NotificationPort;
import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.application.ports.out.TaskRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;

import java.util.UUID;

/**
 * Implementación del caso de uso para activar proyectos.
 * 
 * Reglas de negocio:
 * - RN-01: Un proyecto solo puede activarse si tiene al menos una tarea activa
 * - RN-02: Solo el propietario puede activar el proyecto
 * - Genera auditoría
 * - Genera notificación
 */
public class ActivateProjectUseCaseImpl implements ActivateProjectUseCase {

    private final ProjectRepositoryPort projectRepository;
    private final TaskRepositoryPort taskRepository;
    private final CurrentUserPort currentUserPort;
    private final AuditLogPort auditLogPort;
    private final NotificationPort notificationPort;

    public ActivateProjectUseCaseImpl(
            ProjectRepositoryPort projectRepository,
            TaskRepositoryPort taskRepository,
            CurrentUserPort currentUserPort,
            AuditLogPort auditLogPort,
            NotificationPort notificationPort) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.currentUserPort = currentUserPort;
        this.auditLogPort = auditLogPort;
        this.notificationPort = notificationPort;
    }

    @Override
    public void execute(UUID projectId) {
        // Obtener el usuario autenticado
        UUID currentUserId = currentUserPort.getCurrentUserId();

        // Buscar el proyecto
        Proyect project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project", projectId));

        // RN-02: Verificar que el usuario sea el propietario
        if (!project.getOwnerId().equals(currentUserId)) {
            throw new UnauthorizedAccessException("You are not the owner of this project");
        }

        // RN-01: Verificar que el proyecto tenga al menos una tarea activa
        long activeTasksCount = taskRepository.countActiveTasksByProjectId(projectId);
        if (activeTasksCount == 0) {
            throw new BusinessRuleException("Cannot activate project without active tasks");
        }

        // Activar el proyecto
        project.setStatus(Proyect.Status.ACTIVE);
        projectRepository.save(project);

        // Generar auditoría
        auditLogPort.register("PROJECT_ACTIVATED", projectId, currentUserId);

        // Generar notificación
        notificationPort.notify(String.format("Project '%s' has been activated", project.getName()));
    }
}
