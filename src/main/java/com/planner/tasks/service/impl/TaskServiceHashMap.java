package com.planner.tasks.service.impl;

import com.planner.tasks.entities.Task;
import com.planner.tasks.service.TaskService;
import com.planner.tasks.service.exceptions.TaskServiceException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// @Service
public class TaskServiceHashMap implements TaskService {
    private final ConcurrentHashMap<String, Task> tasks = new ConcurrentHashMap<>();

    @Override
    public Task create(Task task) {
        tasks.putIfAbsent(task.getId(), task);
        return task;
    }

    @Override
    public Task findById(String id) throws TaskServiceException {
        Optional<Task> optionalTask = Optional.ofNullable(tasks.get(id));
        optionalTask.orElseThrow(() -> new TaskServiceException(TaskServiceException.TASK_NOT_FOUND));

        return optionalTask.get();
    }

    @Override
    public List<Task> getAll() {
        List<Task> allTasks = new ArrayList<>();

        for (String key : tasks.keySet()) {
            allTasks.add(tasks.get(key));
        }

        return allTasks;
    }

    @Override
    public Task deleteById(String id) throws TaskServiceException {
        if (!tasks.containsKey(id)) {
            throw new TaskServiceException(TaskServiceException.TASK_NOT_FOUND);
        }

        return tasks.remove(id);
    }

    @Override
    public Task update(Task task, String id) throws TaskServiceException {
        if (!tasks.containsKey(id)) {
            throw new TaskServiceException(TaskServiceException.TASK_NOT_FOUND);
        }

        tasks.replace(id, task);
        return tasks.get(id);
    }
}
