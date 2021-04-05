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
@SequenceGenerator(name = "employee_seq", initialValue = 101, allocationSize = 1)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    private Integer id;
    private String firstName;
    private String lastName;
    private String hireDate; // not messing with dates for a demo
    @ManyToOne
    private Department department;
    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Project> projects;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Task> tasks;
    @OneToOne
    private Job job;

    public Employee(String firstName, String lastName, String hireDate, Department department, Job job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.department = department;
        this.job = job;
    }
}

// ManyToOne = when unidirectional, identified on the One side of the relationship

// OneToMany = for both unidirectional and bidirectional, identified on the One side as a List of the Many
//      When bidirectional, identified on the Many side by ManyToOne
//      Always a List type.

// OneToOne = may be uni- or bi-directional.
//      Bidirectional example: Breaking out optional details to separate table when one table gets too many columns.
//      Unidirectional example: Referencing small categorizing tables used by several other tables.

// MapsId = used with bidirectional OneToOne to indicate the ID field comes from the owning side of the relationship
//      This annotation goes on the field representing the owning side of the relationship, not the actual ID field.
//      Cannot use ID generator within a class when using MapsId annotation because ID comes from owning class, not this one.

// ManyToMany = When unidirectional, identified on the owning side of the relationship
//      When bidirectional, identified on both sides of relationship
//      Always a Set type. Set is a collection that does not allow duplicates.

// mappedBy parameter identifies owning field in owner of the relationship
//      Can be used by OneToOne, OneToMany, and ManyToMany. Not required.
//      The mappedBy parameter must used by the non-owning side of the relationship.
//      When using this parameter, a class does not make modifiers for a Set with ManyToMany annotation