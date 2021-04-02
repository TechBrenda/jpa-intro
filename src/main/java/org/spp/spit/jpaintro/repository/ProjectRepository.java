package org.spp.spit.jpaintro.repository;

import org.spp.spit.jpaintro.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query(value = "select p from Project p join p.employees e where e.id = :employeeId")
    List<Project> findByEmployee_Id(Integer employeeId);
}
