package org.spp.spit.jpaintro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "task_seq", allocationSize = 1)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Integer id;
    private String summary;
    private String status;
    @ManyToOne
    @JsonBackReference
    private Project project;
    @ManyToOne
    @JsonBackReference
    private Employee employee;

    public Task(String summary, String status, Project project, Employee employee) {
        this.summary = summary;
        this.status = status;
        this.project = project;
        this.employee = employee;
    }
}

// ManyToOne = when bidirectional, used on the Many side of the relationship