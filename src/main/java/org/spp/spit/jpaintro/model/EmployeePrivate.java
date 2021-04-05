package org.spp.spit.jpaintro.model;

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
public class EmployeePrivate {
    @Id
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;
    private Integer salary;
    private String city;

    public EmployeePrivate(Employee employee, Integer salary, String city) {
        this.employee = employee;
        this.salary = salary;
        this.city = city;
    }
}
