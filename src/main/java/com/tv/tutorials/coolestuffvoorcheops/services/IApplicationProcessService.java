package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

import com.tv.tutorials.coolestuffvoorcheops.models.ApplicationProcess;

public interface IApplicationProcessService {

	List<ApplicationProcess> getAllApplicationProcesses();

	ApplicationProcess getApplicationProcessById(int applicationProcessId);

	ApplicationProcess addApplicationProcess(ApplicationProcess applicationProcess);

	void updateApplicationProcess(ApplicationProcess applicationProcess);

	void deleteApplicationProcess(int applicationProcessId);
}
