package com.thoughtworks.nho.olsapi.camp;

import javax.persistence.Entity;
import java.util.UUID;


public class Task {
    private UUID id;
    private String title;
    private String description;
    private String createdTime;

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public Task(String title, String description, String createdTime) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.createdTime = createdTime;
    }

    public Task() {
    }
}
