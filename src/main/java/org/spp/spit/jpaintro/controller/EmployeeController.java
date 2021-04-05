package org.spp.spit.jpaintro.controller;

import org.spp.spit.jpaintro.exception.DepartmentNotFoundException;
import org.spp.spit.jpaintro.exception.EmployeeNotFoundException;
import org.spp.spit.jpaintro.exception.JobNotFoundException;
import org.spp.spit.jpaintro.model.Employee;
import org.spp.spit.jpaintro.repository.DepartmentRepository;
import org.spp.spit.jpaintro.repository.EmployeeRepository;
import org.spp.spit.jpaintro.repository.JobRepository;
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

    // Class with single constructor may omit @Autowired annotation.
    public EmployeeController(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.jobRepository = jobRepository;
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
        employeeRepository.deleteById(id);
    }
}
