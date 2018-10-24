package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cheops.candidatemanager.models.ApplicationProcess;
import com.cheops.candidatemanager.repositories.ApplicationProcessRepository;
import com.cheops.candidatemanager.services.IApplicationProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void deleteApplicationProcess(int applicationProcessId) {
		applicationProcessRepository.delete(getApplicationProcessById(applicationProcessId));
	}

	public List<Integer> findAllRecruited() {
		boolean isRecruited = true;
		List<ApplicationProcess> list = applicationProcessRepository.findAllByIsRecruited(isRecruited);

		List<Integer> applicationProcessId = new ArrayList<Integer>();
		for (ApplicationProcess applicationProcess : list) {
			applicationProcessId.add(applicationProcess.getId());
		}
		return applicationProcessId;
	}

	public List<Integer> getAllCandidatesWithActiveApplicationProcess() {
		boolean isToBeInvitedForFirstConversation = true;
		ArrayList<ApplicationProcess> list = (ArrayList<ApplicationProcess>) applicationProcessRepository
				.findAllBytoBeInvitedForFirstConversation(isToBeInvitedForFirstConversation);

		Iterator<ApplicationProcess> i = list.iterator();
		while (i.hasNext()) {
			ApplicationProcess s = i.next(); // must be called before you can call i.remove()
			// Do something
			if (s.getIsRecruited()) {
				i.remove();
			}
		}

		List<Integer> applicationProcessId = new ArrayList<Integer>();
		for (ApplicationProcess applicationProcess : list) {
			applicationProcessId.add(applicationProcess.getId());
		}
		return applicationProcessId;
	}

	public List<Integer> getAllCandidatesWithoutActiveApplicationProcess() {
		boolean isToBeInvitedForFirstConversation = false;
		List<ApplicationProcess> list = applicationProcessRepository
				.findAllBytoBeInvitedForFirstConversation(isToBeInvitedForFirstConversation);
		List<Integer> applicationProcessId = new ArrayList<Integer>();
		for (ApplicationProcess applicationProcess : list) {
			applicationProcessId.add(applicationProcess.getId());
		}
		return applicationProcessId;
	}

	public List<ApplicationProcess> getAllApplicationProcessById(List<Integer> applicationProcessId) {
		return (List<ApplicationProcess>) applicationProcessRepository.findAllById(applicationProcessId);
	}

	public List<ApplicationProcess> findAllByIsRecruitedIn(List<ApplicationProcess> applicationProcess) {

		Iterator<ApplicationProcess> i = applicationProcess.iterator();
		while (i.hasNext()) {
			ApplicationProcess s = i.next(); // must be called before you can call i.remove()
			// Do something
			if (!(s.getIsRecruited())) {
				i.remove();
			}
		}

		return applicationProcess;
	}

}
