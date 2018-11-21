package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.ApplicationProcess;

public interface IApplicationProcessService {

	List<ApplicationProcess> getAllApplicationProcesses();

	ApplicationProcess getApplicationProcessById(int applicationProcessId);

	ApplicationProcess addApplicationProcess(ApplicationProcess applicationProcess);

	void updateApplicationProcess(ApplicationProcess applicationProcess);

	void deleteApplicationProcess(int applicationProcessId);

	List<ApplicationProcess> getUpcomingMonthsFirstScreenings();

  List<ApplicationProcess> getUpcomingMonthsTechnicalScreenings();

	List<Integer> findAllRecruited();

  List<Integer> getAllCandidatesWithActiveApplicationProcess();

  List<Integer> getAllCandidatesWithoutActiveApplicationProcess();

  List<ApplicationProcess> getAllApplicationProcessById(List<Integer> applicationProcessId);

  List<ApplicationProcess> findAllByIsRecruitedIn(List<ApplicationProcess> applicationProcess);

  List<ApplicationProcess> getLast5Recruited();

  List<ApplicationProcess> getLastMonthsRecruits();

  List<Integer> getAllNotRecuitedCandidates();

  List<Integer> getAllExEmployees();

}
