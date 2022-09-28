package com.planner.tasks.repository;

import com.planner.tasks.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String>{
}
