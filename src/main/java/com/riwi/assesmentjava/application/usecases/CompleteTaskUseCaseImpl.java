package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.exception.BusinessRuleException;
import com.riwi.assesmentjava.application.exception.EntityNotFoundException;
import com.riwi.assesmentjava.application.exception.UnauthorizedAccessException;
import com.riwi.assesmentjava.application.ports.in.CompleteTaskUseCase;
import com.riwi.assesmentjava.application.ports.out.AuditLogPort;
import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.application.ports.out.NotificationPort;
import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.application.ports.out.TaskRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;
import com.riwi.assesmentjava.domain.model.Tasks;

import java.util.UUID;

/**
 * Implementación del caso de uso para completar tareas.
 * 
 * Reglas de negocio:
 * - RN-03: Una tarea completada no puede modificarse
 * - RN-02: Solo el propietario del proyecto puede completar la tarea
 * - Genera auditoría
 * - Genera notificación
 */
public class CompleteTaskUseCaseImpl implements CompleteTaskUseCase {

    private final TaskRepositoryPort taskRepository;
    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;
    private final AuditLogPort auditLogPort;
    private final NotificationPort notificationPort;

    public CompleteTaskUseCaseImpl(
            TaskRepositoryPort taskRepository,
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort,
            AuditLogPort auditLogPort,
            NotificationPort notificationPort) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
        this.auditLogPort = auditLogPort;
        this.notificationPort = notificationPort;
    }

    @Override
    public void execute(UUID taskId) {
        // Obtener el usuario autenticado
        UUID currentUserId = currentUserPort.getCurrentUserId();

        // Buscar la tarea
        Tasks task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task", taskId));

        // RN-03: Verificar que la tarea no esté ya completada
        if (task.isCompleted()) {
            throw new BusinessRuleException("Task is already completed and cannot be modified");
        }

        // Buscar el proyecto para verificar propiedad
        Proyect project = projectRepository.findById(task.getProyectId())
                .orElseThrow(() -> new EntityNotFoundException("Project", task.getProyectId()));

        // RN-02: Verificar que el usuario sea el propietario del proyecto
        if (!project.getOwnerId().equals(currentUserId)) {
            throw new UnauthorizedAccessException("You are not the owner of this project");
        }

        // Completar la tarea
        task.setCompleted(true);
        taskRepository.save(task);

        // Generar auditoría
        auditLogPort.register("TASK_COMPLETED", taskId, currentUserId);

        // Generar notificación
        notificationPort.notify(String.format("Task '%s' has been completed", task.getTitle()));
    }
}
