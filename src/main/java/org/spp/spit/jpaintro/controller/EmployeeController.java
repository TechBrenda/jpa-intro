package org.spp.spit.jpaintro.controller;

import org.spp.spit.jpaintro.exception.DepartmentNotFoundException;
import org.spp.spit.jpaintro.exception.EmployeeNotFoundException;
import org.spp.spit.jpaintro.exception.JobNotFoundException;
import org.spp.spit.jpaintro.model.Employee;
import org.spp.spit.jpaintro.model.Project;
import org.spp.spit.jpaintro.model.Task;
import org.spp.spit.jpaintro.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")   // class level API path
public class EmployeeController {

    // Repository fields are private and final.
    // Use Constructor or Setter injection (Autowired), not Field injection.
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    // Class with single constructor may omit @Autowired annotation.
    public EmployeeController(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            JobRepository jobRepository,
            ProjectRepository projectRepository,
            TaskRepository taskRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.jobRepository = jobRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public List<Employee> all() {
        return employeeRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Employee one(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping("/")
    public Employee newEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(path = "/{id}")
    // Depending on frontend to update existing employee so all fields are set.
    public Employee replaceEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setFirstName(employee.getFirstName());
                    emp.setLastName(employee.getLastName());
                    departmentRepository.findById(employee.getDepartment().getId())
                            .orElseThrow(() -> new DepartmentNotFoundException(employee.getDepartment().getId()));
                    emp.setDepartment(employee.getDepartment());
                    jobRepository.findById(employee.getJob().getId())
                            .orElseThrow(() -> new JobNotFoundException(employee.getJob().getId()));
                    emp.setJob(employee.getJob());
                    emp.setHireDate(employee.getHireDate());
                    return employeeRepository.save(emp);
                })
                .orElseGet(() -> {
                    employee.setId(id);
                    return employeeRepository.save(employee);
                });
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        List<Project> projects = projectRepository.findByEmployee_Id(id);
        for (Project project : projects) {
            if (project.getEmployees().size() == 1) {
                project.addEmployee(employeeRepository.findByJob_Id(1));
            }
            project.removeEmployee(employee);
        }
        List<Task> tasks = taskRepository.findByEmployee_Id(id);
        for (Task task : tasks) {
            task.setEmployee(employeeRepository.findByJob_Id(1));
        }
        employeeRepository.deleteById(id);
    }
}
