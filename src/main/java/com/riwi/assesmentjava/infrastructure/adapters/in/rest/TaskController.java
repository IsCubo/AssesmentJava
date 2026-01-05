package com.riwi.assesmentjava.infrastructure.adapters.in.rest;

import com.riwi.assesmentjava.application.dto.CreateTaskCommand;
import com.riwi.assesmentjava.application.dto.TaskDTO;
import com.riwi.assesmentjava.application.ports.in.CompleteTaskUseCase;
import com.riwi.assesmentjava.application.ports.in.CreateTaskUseCase;
import com.riwi.assesmentjava.application.ports.in.GetProjectTasksUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Gestión de Tareas")
@SecurityRequirement(name = "bearerAuth")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;
    private final GetProjectTasksUseCase getProjectTasksUseCase;

    @Operation(summary = "Crear tarea", description = "Añade una nueva tarea a un proyecto existente.")
    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<TaskDTO> createTask(
            @PathVariable UUID projectId,
            @RequestBody CreateTaskCommand command) {

        command.setProjectId(projectId);

        TaskDTO task = createTaskUseCase.execute(command);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar tareas de proyecto", description = "Obtiene todas las tareas asociadas a un proyecto.")
    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<List<TaskDTO>> getProjectTasks(@PathVariable UUID projectId) {
        List<TaskDTO> tasks = getProjectTasksUseCase.execute(projectId);
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Completar tarea", description = "Marca una tarea como completada, activa auditoría y notificaciones.")
    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable UUID id) {
        completeTaskUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
