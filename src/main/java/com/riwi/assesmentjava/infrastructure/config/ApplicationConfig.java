package com.riwi.assesmentjava.infrastructure.config;

import com.riwi.assesmentjava.application.ports.in.*;
import com.riwi.assesmentjava.application.ports.out.*;
import com.riwi.assesmentjava.application.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateProjectUseCase createProjectUseCase(
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        return new CreateProjectUseCaseImpl(projectRepository, currentUserPort);
    }

    @Bean
    public ActivateProjectUseCase activateProjectUseCase(
            ProjectRepositoryPort projectRepository,
            TaskRepositoryPort taskRepository,
            CurrentUserPort currentUserPort,
            AuditLogPort auditLogPort,
            NotificationPort notificationPort) {
        return new ActivateProjectUseCaseImpl(projectRepository, taskRepository, currentUserPort, auditLogPort,
                notificationPort);
    }

    @Bean
    public GetUserProjectsUseCase getUserProjectsUseCase(
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        return new GetUserProjectsUseCaseImpl(projectRepository, currentUserPort);
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase(
            TaskRepositoryPort taskRepository,
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        return new CreateTaskUseCaseImpl(taskRepository, projectRepository, currentUserPort);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(
            TaskRepositoryPort taskRepository,
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort,
            AuditLogPort auditLogPort,
            NotificationPort notificationPort) {
        return new CompleteTaskUseCaseImpl(taskRepository, projectRepository, currentUserPort, auditLogPort,
                notificationPort);
    }

    @Bean
    public GetProjectTasksUseCase getProjectTasksUseCase(
            TaskRepositoryPort taskRepository,
            ProjectRepositoryPort projectRepository,
            CurrentUserPort currentUserPort) {
        return new GetProjectTasksUseCaseImpl(taskRepository, projectRepository, currentUserPort);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepositoryPort userRepositoryPort) {
        return new RegisterUserUseCaseImpl(userRepositoryPort);
    }
}
