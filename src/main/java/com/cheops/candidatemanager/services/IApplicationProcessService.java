package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.ApplicationProcess;

public interface IApplicationProcessService {

	List<ApplicationProcess> getAllApplicationProcesses();

	ApplicationProcess getApplicationProcessById(int applicationProcessId);

	ApplicationProcess addApplicationProcess(ApplicationProcess applicationProcess);

	void updateApplicationProcess(ApplicationProcess applicationProcess);

	void deleteApplicationProcess(int applicationProcessId);
}
