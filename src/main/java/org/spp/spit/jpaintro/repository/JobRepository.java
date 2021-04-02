package org.spp.spit.jpaintro.repository;

import org.spp.spit.jpaintro.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

}
