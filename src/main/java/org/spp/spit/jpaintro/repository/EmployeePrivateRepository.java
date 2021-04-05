package org.spp.spit.jpaintro.repository;

import org.spp.spit.jpaintro.model.EmployeePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePrivateRepository extends JpaRepository<EmployeePrivate, Integer> {
}
