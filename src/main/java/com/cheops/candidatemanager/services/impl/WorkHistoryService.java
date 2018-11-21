package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cheops.candidatemanager.services.IWorkHistoryService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.models.WorkHistory;
import com.cheops.candidatemanager.repositories.WorkHistoryRepository;

@Service
public class WorkHistoryService implements IWorkHistoryService {

	@Autowired
	private WorkHistoryRepository workHistoryRepository;

	@Override
	public List<WorkHistory> getLastMonthsRecruits() {
		Date lastMonth = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		lastMonth = DateUtils.addMonths(new Date(), -1);
		return workHistoryRepository.findAllByStartedGreaterThanEqual(lastMonth);
	}

	@Override
	public List<WorkHistory> getLast5Recruited() {
		return workHistoryRepository.findTop5ByOrderByStartedDesc();
	}

	@Override
	public List<WorkHistory> getAllExEmployees() {
		List<WorkHistory> possibleExEmployees = workHistoryRepository.findAllByStoppedNotNull();
		List<WorkHistory> toRemove = new ArrayList<WorkHistory>();
		boolean isExEmployee = true;

		for (WorkHistory workHistory : possibleExEmployees) {
			List<WorkHistory> workHistoryToCheckForBeingExemployeeList = workHistoryRepository.findAllByCandidateId(workHistory.getCandidateId());

			if (workHistoryToCheckForBeingExemployeeList.size() > 1) {
				Date maxStartDate = workHistoryToCheckForBeingExemployeeList.stream().map(WorkHistory::getStarted).max(Date::compareTo).get();
				List<WorkHistory> workHistoryWithStoppedotNullList = new ArrayList<WorkHistory>();

				for (WorkHistory workHistory2 : workHistoryToCheckForBeingExemployeeList) {
					if (workHistory2.getStopped() != null) workHistoryWithStoppedotNullList.add(workHistory2);
				}

				Date maxStoppedDate = workHistoryWithStoppedotNullList.stream().map(WorkHistory::getStopped).max(Date::compareTo).get();
				if (maxStartDate.after(maxStoppedDate)) isExEmployee = false;
			}

			if (!isExEmployee) toRemove.add(workHistory);
		}

		possibleExEmployees.removeAll(toRemove);
		return possibleExEmployees;
	}

}
