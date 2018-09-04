package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

//import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProces;
import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;

public interface IApplicationProcessService {

	
	 List<ApplicationProcess> getAllApplicationProcesses();
	ApplicationProcess getApplicationProcessById(int applicationProcessId);
     ApplicationProcess addApplicationProcess(ApplicationProcess applicationProcess);
     void updateApplicationProcess(ApplicationProcess applicationProcess);
     void deleteAdress(int applicationProcessId);
}
