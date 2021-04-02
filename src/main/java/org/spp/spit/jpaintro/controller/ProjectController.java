package org.spp.spit.jpaintro.controller;

import org.spp.spit.jpaintro.model.Project;
import org.spp.spit.jpaintro.repository.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/")
    public List<Project> all() {
        return projectRepository.findAll();
    }
}
