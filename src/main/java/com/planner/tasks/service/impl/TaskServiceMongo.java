package com.planner.tasks.service.impl;

import com.planner.tasks.entities.Task;
import com.planner.tasks.repository.TaskRepository;
import com.planner.tasks.service.TaskService;
import com.planner.tasks.service.exceptions.TaskServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceMongo implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceMongo(@Autowired TaskRepository taskRepository) { this.taskRepository = taskRepository; };

    @Override
    public Task create(Task task) {
        taskRepository.save(task);

        return task;
    }

    @Override
    public Task findById(String id) throws TaskServiceException {
        return taskRepository.findById(id).get();
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task deleteById(String id) throws TaskServiceException {
        if (findById(id) == null) {
            throw new TaskServiceException(TaskServiceException.TASK_NOT_FOUND);
        }

        Task deletedTask = findById(id);
        taskRepository.deleteById(id);

        return deletedTask;
    }

    @Override
    public Task update(Task task, String id) throws TaskServiceException {
        Task taskToUpdate = findById(id);
        taskToUpdate.update(task);

        return taskRepository.save(task);
    }
}
