package com.riwi.assesmentjava.domain.model;

import java.util.UUID;

public class Tasks {
    private UUID id;
    private UUID proyectId;
    private String title;
    private boolean completed;
    private boolean deleted;

    public Tasks() {
    }

    public Tasks(UUID id, UUID proyectId, String title, boolean completed, boolean deleted) {
        this.id = id;
        this.proyectId = proyectId;
        this.title = title;
        this.completed = completed;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProyectId() {
        return proyectId;
    }

    public void setProyectId(UUID proyectId) {
        this.proyectId = proyectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "completed=" + completed +
                ", title='" + title + '\'' +
                ", proyectId=" + proyectId +
                ", id=" + id +
                '}';
    }
}
