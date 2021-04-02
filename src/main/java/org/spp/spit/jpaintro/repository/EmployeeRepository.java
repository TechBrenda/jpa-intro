package org.spp.spit.jpaintro.repository;

import org.spp.spit.jpaintro.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByJob_Id(Integer jobId);
}
