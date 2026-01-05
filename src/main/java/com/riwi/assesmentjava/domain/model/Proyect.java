package com.riwi.assesmentjava.domain.model;

import java.util.UUID;

public class Proyect {
    private UUID id;
    private UUID ownerId;
    private String name;
    private Status status;
    private boolean deleted;

    public Proyect() {
    }

    public Proyect(UUID id, UUID ownerId, String name, Status status, boolean deleted) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.status = status;
        this.deleted = deleted;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Proyect{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    public enum Status{
        DRAFT,
        ACTIVE
    }
}

