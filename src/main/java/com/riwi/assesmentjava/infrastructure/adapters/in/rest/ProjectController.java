package com.riwi.assesmentjava.infrastructure.adapters.in.rest;

import com.riwi.assesmentjava.application.dto.CreateProjectCommand;
import com.riwi.assesmentjava.application.dto.ProjectDTO;
import com.riwi.assesmentjava.application.ports.in.ActivateProjectUseCase;
import com.riwi.assesmentjava.application.ports.in.CreateProjectUseCase;
import com.riwi.assesmentjava.application.ports.in.GetUserProjectsUseCase;
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
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@Tag(name = "Projects", description = "Gestión de Proyectos")
@SecurityRequirement(name = "bearerAuth")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final ActivateProjectUseCase activateProjectUseCase;
    private final GetUserProjectsUseCase getUserProjectsUseCase;

    @Operation(summary = "Crear nuevo proyecto", description = "Crea un proyecto en estado DRAFT asociado al usuario actual.")
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody CreateProjectCommand command) {
        ProjectDTO project = createProjectUseCase.execute(command);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar proyectos del usuario", description = "Obtiene todos los proyectos donde el usuario es dueño.")
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getUserProjects() {
        List<ProjectDTO> projects = getUserProjectsUseCase.execute();
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Activar proyecto", description = "Cambia el estado de un proyecto a ACTIVE si cumple las reglas de negocio (tiene tareas activas).")
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activateProject(@PathVariable UUID id) {
        activateProjectUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
