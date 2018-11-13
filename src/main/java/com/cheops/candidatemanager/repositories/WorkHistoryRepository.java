package com.cheops.candidatemanager.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cheops.candidatemanager.models.WorkHistory;

public interface WorkHistoryRepository extends CrudRepository<WorkHistory, Integer> {

	List<WorkHistory> findAllByStartedGreaterThanEqual(Date lastMonth);

	List<WorkHistory> findTop5ByOrderByStartedDesc();

	List<WorkHistory> findAllByStoppedNotNull();

	List<WorkHistory> findAllByCandidateId(int candidateId);

}
