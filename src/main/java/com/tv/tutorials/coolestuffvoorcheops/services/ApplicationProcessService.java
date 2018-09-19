package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.ApplicationProcessRepository;

@Service
public class ApplicationProcessService implements IApplicationProcessService {

	@Autowired
	private ApplicationProcessRepository applicationProcessRepository;
	
	@Override
	public List<ApplicationProcess> getAllApplicationProcesses() {
		List<ApplicationProcess> list = new ArrayList<>();
		applicationProcessRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public ApplicationProcess getApplicationProcessById(int applicationProcessId) {
		System.out.println("id is " +applicationProcessId);
		ApplicationProcess e = applicationProcessRepository.findById(applicationProcessId).get();
		return e;
	}

	@Override
	public ApplicationProcess addApplicationProcess(ApplicationProcess applicationProcess) {
		return applicationProcessRepository.save(applicationProcess);
		
	}

	@Override
	public void updateApplicationProcess(ApplicationProcess applicationProcess) {
		applicationProcessRepository.save(applicationProcess);
	}

	@Override
	public void deleteAdress(int applicationProcessId) {
		applicationProcessRepository.delete(getApplicationProcessById(applicationProcessId));
		
	}

	public void saveOrUpdateApplicationProcess(int id ,@Valid ApplicationProcess applicationProcess) {
		Optional<ApplicationProcess> tmp = applicationProcessRepository.findById(id);
		if (tmp.isPresent() ) {
			ApplicationProcess s =tmp.get();			
			s= applicationProcess;
			s.setId(id);
			applicationProcessRepository.save(s);
		}
		else {		
			applicationProcessRepository.save(applicationProcess);
		}
		
	}

	public List<Integer> findAllRecruited() {
		boolean isRecruited = true;
		List<ApplicationProcess> list =  applicationProcessRepository.findAllByIsRecruited(isRecruited);
		
		List<Integer> applicationProcessId = new ArrayList<Integer>();
		for (ApplicationProcess applicationProcess : list) {
			applicationProcessId.add(applicationProcess.getId());
		}
		return applicationProcessId;
	}

}
