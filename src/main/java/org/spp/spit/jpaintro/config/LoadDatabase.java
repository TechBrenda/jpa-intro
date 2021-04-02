package org.spp.spit.jpaintro.config;

import lombok.extern.slf4j.Slf4j;
import org.spp.spit.jpaintro.model.*;
import org.spp.spit.jpaintro.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(DepartmentRepository departmentRepository,
                                   JobRepository jobRepository,
                                   EmployeeRepository employeeRepository,
                                   ProjectRepository projectRepository,
                                   TaskRepository taskRepository) {
        return args -> {
            Department d1 = departmentRepository.save(new Department("Development"));
            log.info("Preloading " + d1);
            Department d2 = departmentRepository.save(new Department("Planning"));
            log.info("Preloading " + d2);

            Job j1 = jobRepository.save(new Job("Business Analyst"));
            log.info("Preloading " + j1);
            Job j2 = jobRepository.save(new Job("Database Analyst"));
            log.info("Preloading " + j2);
            Job j3 = jobRepository.save(new Job("Spring Developer"));
            log.info("Preloading " + j3);
            Job j4 = jobRepository.save(new Job("React Developer"));
            log.info("Preloading " + j4);
            Job j5 = jobRepository.save(new Job("Frontend Designer"));
            log.info("Preloading " + j5);
            Job j6 = jobRepository.save(new Job("Wireframe Specialist"));
            log.info("Preloading " + j6);

            Employee e1 = employeeRepository.save(new Employee("Jane", "Lane", "01/01/2021", d2, j1));
            log.info("Preloading " + e1);
            Employee e2 = employeeRepository.save(new Employee("Steve", "Roberts", "10/01/2020", d1, j2));
            log.info("Preloading " + e2);
            Employee e3 = employeeRepository.save(new Employee("Kate", "Dunlap", "03/01/2014", d1, j3));
            log.info("Preloading " + e3);
            Employee e4 = employeeRepository.save(new Employee("Alex", "Chase", "02/22/2017", d1, j4));
            log.info("Preloading " + e4);
            Employee e5 = employeeRepository.save(new Employee("Serena", "Jones", "01/01/2017", d1, j5));
            log.info("Preloading " + e5);
            Employee e6 = employeeRepository.save(new Employee("Clement", "Williams", "01/01/2021", d2, j6));
            log.info("Preloading " + e6);

            Project p1 = projectRepository.save(new Project("Budgeting App", new HashSet<>(Arrays.asList(e2, e3, e4, e5))));
            log.info("Preloading " + p1);
            Project p2 = projectRepository.save(new Project("Meal Planning App", new HashSet<>(Arrays.asList(e1, e5, e6))));
            log.info("Preloading " + p2);

            Task t1p1 = taskRepository.save(new Task("Upgrade database", "In Progress", p1, e2));
            log.info("Preloading " + t1p1);
            Task t2p1 = taskRepository.save(new Task("Add Fund to model", "In Progress", p1, e3));
            log.info("Preloading " + t2p1);
            Task t3p1 = taskRepository.save(new Task("Add Fund repository", "In Progress", p1, e3));
            log.info("Preloading " + t3p1);
            Task t4p1 = taskRepository.save(new Task("Add Fund controller", "In Progress", p1, e3));
            log.info("Preloading " + t4p1);
            Task t5p1 = taskRepository.save(new Task("Add Fund component", "In Progress", p1, e4));
            log.info("Preloading " + t5p1);
            Task t6p1 = taskRepository.save(new Task("Add new color style for Fund component", "In Progress", p1, e5));
            log.info("Preloading " + t6p1);
            Task t1p2 = taskRepository.save(new Task("Submit final draft of requirements", "In Progress", p2, e1));
            log.info("Preloading " + t1p2);
            Task t2p2 = taskRepository.save(new Task("Review wireframe with designer", "In Progress", p2, e6));
            log.info("Preloading " + t2p2);
        };
    }
}
