package org.spp.spit.jpaintro.controller;

import org.spp.spit.jpaintro.model.Task;
import org.spp.spit.jpaintro.repository.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public List<Task> all() {
        return taskRepository.findAll();
    }

    @GetMapping("/{employeeId}")
    public List<Task> getByEmployeeId(@PathVariable Integer employeeId) {
        return taskRepository.findByEmployee_Id(employeeId);
    }
}
