package org.spp.spit.jpaintro.controller;

import org.spp.spit.jpaintro.model.Department;
import org.spp.spit.jpaintro.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/")
    public List<Department> all() {
        return departmentRepository.findAll();
    }
}
