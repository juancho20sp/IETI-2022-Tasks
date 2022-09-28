package com.planner.tasks.entities;

import com.planner.tasks.utils.Status;

import java.util.UUID;

public class Task {
    private String id;
    private String name;
    private String description;
    private Status status;
    private String assignedTo;
    private String dueDate;
    private String createdAt;

    public Task(){
        this.id = UUID.randomUUID().toString();
        this.createdAt = java.time.LocalDate.now().toString();
    }

    public Task(String name, String description, Status status, String assignedTo, String dueDate){
        this();
        this.name = name;
        this.description = description;
        this.status = status;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
    }

    public Task(String id, String name, String description, Status status, String assignedTo, String dueDate){
        this(name,  description,  status,  assignedTo, dueDate);
        this.id = id;
    }

    public void update(Task task) {
        name = task.getName();
        description = task.getDescription();
        status = task.getStatus();
        assignedTo = task.getAssignedTo();
        dueDate = task.getDueDate();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
