package com.cheops.candidatemanager.repositories;

import com.cheops.candidatemanager.models.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
  List<Candidate> findAllByIdIn(List<Integer> candidateIds);
  List<Candidate> findFirst3ByOrderByIdDesc();
  List<Candidate> findAllBySkillIdIn(List<Integer> skillsIds);
  List<Candidate> findAllByNameLikeOrLastNameLike(String name, String lastName);
  List<Candidate> findAllByApplicationProcessIdIn(List<Integer> applicationProcessIds);
  Candidate findByapplicationProcessId(Integer id);
}
