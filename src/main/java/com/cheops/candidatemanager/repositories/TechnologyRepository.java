package com.cheops.candidatemanager.repositories;

import com.cheops.candidatemanager.models.Technology;
import org.springframework.data.repository.CrudRepository;

public interface TechnologyRepository extends CrudRepository<Technology, Integer> {
  Technology findDistinctFirstByName(String name);
}
