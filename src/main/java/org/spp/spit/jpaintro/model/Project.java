package org.spp.spit.jpaintro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "project_seq", allocationSize = 1)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    private Integer id;
    private String title;
    @ManyToMany
    @JsonBackReference
    private Set<Employee> employees;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Task> tasks;

    public Project(String title, Set<Employee> employees) {
        this.title = title;
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }
}

// ManyToMany bidirectional owner = The owner of the relationship manages the content of the set for the other member
//      of the relationship. If both have these modifiers, it causes endless loop.