package com.planner.tasks.service.exceptions;

public class TaskServiceException extends Exception {
    public static final String TASK_NOT_FOUND = "Task not found in database";

    public TaskServiceException() {
        super();
    }

    public TaskServiceException(String message) {
        super(message);
    }

    public TaskServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
