package com.riwi.assesmentjava.application.usecases;

import com.riwi.assesmentjava.application.exception.BusinessRuleException;
import com.riwi.assesmentjava.application.exception.UnauthorizedAccessException;
import com.riwi.assesmentjava.application.ports.out.AuditLogPort;
import com.riwi.assesmentjava.application.ports.out.CurrentUserPort;
import com.riwi.assesmentjava.application.ports.out.NotificationPort;
import com.riwi.assesmentjava.application.ports.out.ProjectRepositoryPort;
import com.riwi.assesmentjava.application.ports.out.TaskRepositoryPort;
import com.riwi.assesmentjava.domain.model.Proyect;
import com.riwi.assesmentjava.domain.model.Tasks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseTests {

    @Mock
    private ProjectRepositoryPort projectRepository;
    @Mock
    private TaskRepositoryPort taskRepository;
    @Mock
    private CurrentUserPort currentUserPort;
    @Mock
    private AuditLogPort auditLogPort;
    @Mock
    private NotificationPort notificationPort;

    @InjectMocks
    private ActivateProjectUseCaseImpl activateProjectUseCase;

    @InjectMocks
    private CompleteTaskUseCaseImpl completeTaskUseCase;

    private UUID userId;
    private UUID projectId;
    private UUID taskId;
    private Proyect project;
    private Tasks task;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        projectId = UUID.randomUUID();
        taskId = UUID.randomUUID();

        project = new Proyect(projectId, userId, "Test Project", Proyect.Status.DRAFT, false);
        task = new Tasks(taskId, projectId, "Test Task", false, false);
    }

    // 1. ActivateProject_WithTasks_ShouldSucceed
    @Test
    void ActivateProject_WithTasks_ShouldSucceed() {
        // Arrange
        when(currentUserPort.getCurrentUserId()).thenReturn(userId);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(taskRepository.countActiveTasksByProjectId(projectId)).thenReturn(1L);

        // Act
        activateProjectUseCase.execute(projectId);

        // Assert
        assertEquals(Proyect.Status.ACTIVE, project.getStatus());
        verify(projectRepository).save(project);
        verify(auditLogPort).register(eq("PROJECT_ACTIVATED"), eq(projectId), eq(userId));
        verify(notificationPort).notify(anyString());
    }

    // 2. ActivateProject_WithoutTasks_ShouldFail
    @Test
    void ActivateProject_WithoutTasks_ShouldFail() {
        // Arrange
        when(currentUserPort.getCurrentUserId()).thenReturn(userId);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(taskRepository.countActiveTasksByProjectId(projectId)).thenReturn(0L);

        // Act & Assert
        assertThrows(BusinessRuleException.class, () -> activateProjectUseCase.execute(projectId));
        assertEquals(Proyect.Status.DRAFT, project.getStatus());
        verify(projectRepository, never()).save(any());
    }

    // 3. ActivateProject_ByNonOwner_ShouldFail
    @Test
    void ActivateProject_ByNonOwner_ShouldFail() {
        // Arrange
        UUID anotherUser = UUID.randomUUID();
        when(currentUserPort.getCurrentUserId()).thenReturn(anotherUser);
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // Act & Assert
        assertThrows(UnauthorizedAccessException.class, () -> activateProjectUseCase.execute(projectId));
        verify(taskRepository, never()).countActiveTasksByProjectId(any());
        verify(projectRepository, never()).save(any());
    }

    // 4. CompleteTask_AlreadyCompleted_ShouldFail
    @Test
    void CompleteTask_AlreadyCompleted_ShouldFail() {
        // Arrange
        when(currentUserPort.getCurrentUserId()).thenReturn(userId);
        task.setCompleted(true); // Already completed
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act & Assert
        assertThrows(BusinessRuleException.class, () -> completeTaskUseCase.execute(taskId));
        verify(taskRepository, never()).save(any());
    }

    // 5. CompleteTask_ShouldGenerateAuditAndNotification
    @Test
    void CompleteTask_ShouldGenerateAuditAndNotification() {
        // Arrange
        when(currentUserPort.getCurrentUserId()).thenReturn(userId);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project)); // Necesario para verificar owner

        // Act
        completeTaskUseCase.execute(taskId);

        // Assert
        assertTrue(task.isCompleted());
        verify(taskRepository).save(task);
        verify(auditLogPort).register(eq("TASK_COMPLETED"), eq(taskId), eq(userId));
        verify(notificationPort).notify(contains("completed"));
    }
}
