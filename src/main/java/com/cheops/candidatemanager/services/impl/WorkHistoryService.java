package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.models.WorkHistory;
import com.cheops.candidatemanager.repositories.WorkHistoryRepository;

@Service
public class WorkHistoryService {

	@Autowired
	private WorkHistoryRepository workHistoryRepository;

	public List<WorkHistory> getLastMonthsRecruits() {
		Date lastMonth = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		lastMonth = DateUtils.addMonths(new Date(), -1);
		List<WorkHistory> l = workHistoryRepository.findAllByStartedGreaterThanEqual(lastMonth);
		System.out.println("Hooil" + l.size());

		return l;
	}

	public List<WorkHistory> getLast5Recruited() {
		List<WorkHistory> l = workHistoryRepository.findTop5ByOrderByStartedDesc();
		System.out.println("Hooikl" + l.size());

		return l;
	}

	public List<WorkHistory> getAllExEmployees() {
		List<WorkHistory> possibleExEmployees = workHistoryRepository.findAllByStoppedNotNull();

		List<WorkHistory> toRemove = new ArrayList<WorkHistory>();

		System.out.println("HooiEXempl" + possibleExEmployees.size());

		boolean isExEmployee = true;
		// wat met TOEKOMSTIGE WERKNEMERS => laten we zo
		for (WorkHistory workHistory : possibleExEmployees) {
			// search if candidateId appears more then ones
			List<WorkHistory> workHistoryToCheckForBeingExemployeeList = workHistoryRepository
					.findAllByCandidateId(workHistory.getCandidateId());
			if (workHistoryToCheckForBeingExemployeeList.size() > 1) {

				Date maxStartDate = workHistoryToCheckForBeingExemployeeList.stream().map(WorkHistory::getStarted)
						.max(Date::compareTo).get();
				System.out.println("max " + maxStartDate);

				List<WorkHistory> workHistoryWithStoppedotNullList = new ArrayList<WorkHistory>();

				for (WorkHistory workHistory2 : workHistoryToCheckForBeingExemployeeList) {
					if (workHistory2.getStopped() != null) {
						workHistoryWithStoppedotNullList.add(workHistory2);
					}
				}

				Date maxStoppedDate = workHistoryWithStoppedotNullList.stream().map(WorkHistory::getStopped)
						.max(Date::compareTo).get();
				System.out.println("maxstopped " + maxStoppedDate);

				if (maxStartDate.after(maxStoppedDate)) {
					isExEmployee = false;
				}

			} else {
				System.out.println("we zijn hier");
			}

			if (!isExEmployee) {
				toRemove.add(workHistory);
			}

		}

		possibleExEmployees.removeAll(toRemove);
		System.out.println("echte -aantal" + possibleExEmployees.size());

		for (WorkHistory workHistory : possibleExEmployees) {
			System.out.println(workHistory.getStopped());
		}

		return possibleExEmployees;
	}

}
