package com.planner.tasks.controller;

import com.planner.tasks.dto.TaskDto;
import com.planner.tasks.entities.Task;
import com.planner.tasks.service.TaskService;
import com.planner.tasks.service.exceptions.TaskServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(@Autowired TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<?> findById( @PathVariable String id ) {
        try {
            return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
        } catch ( TaskServiceException ex ) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Task> create( @RequestBody TaskDto taskDto ) {
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(taskDto, Task.class);

        return new ResponseEntity<>(taskService.create(task), HttpStatus.CREATED);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<?> update( @RequestBody TaskDto taskDto, @PathVariable String id ) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Task taskMapped = modelMapper.map(taskDto, Task.class);

            return new ResponseEntity<>(taskService.update(taskMapped, id), HttpStatus.OK);
        } catch (TaskServiceException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> delete( @PathVariable String id ) {
        try {
            return new ResponseEntity<>(taskService.deleteById(id), HttpStatus.OK);
        } catch (TaskServiceException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
