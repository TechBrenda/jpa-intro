package org.spp.spit.jpaintro.controller;

import org.spp.spit.jpaintro.model.Job;
import org.spp.spit.jpaintro.repository.JobRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping("/")
    public List<Job> all() {
        return jobRepository.findAll();
    }
}
