package com.planner.tasks.service;

import com.planner.tasks.entities.Task;
import com.planner.tasks.service.exceptions.TaskServiceException;

import java.util.List;

public interface TaskService {
    Task create(Task task);

    Task findById( String id ) throws TaskServiceException;

    List<Task> getAll();

    Task deleteById(String id) throws TaskServiceException;

    Task update(Task task, String id) throws TaskServiceException;
}
