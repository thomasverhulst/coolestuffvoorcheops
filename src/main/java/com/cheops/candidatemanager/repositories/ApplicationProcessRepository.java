package com.cheops.candidatemanager.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cheops.candidatemanager.models.ApplicationProcess;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationProcessRepository extends CrudRepository<ApplicationProcess, Integer> {
	List<ApplicationProcess> findAllByIsRecruited(boolean isRecruited);
	List<ApplicationProcess> findAllBytoBeInvitedForFirstConversation(boolean isToBeInvitedForFirstConversation);
	List<ApplicationProcess> findAllByIsRecruitedIn(List<ApplicationProcess> t);
	List<ApplicationProcess> findAllByfirstConversationDateBetween(Date today, Date plusAMonth);
	List<ApplicationProcess> findAllBytechnicalConversationDateBetween(Date today, Date todayg);
	List<ApplicationProcess> findAllByOrderByIsRecruitedTimeStampAsc();
	List<ApplicationProcess> findTop5ByOrderByIsRecruitedTimeStampDesc();
	List<ApplicationProcess> findAllByIsRecruitedTimeStampGreaterThanEqual(Date lastMonth);
	List<ApplicationProcess> findAllByNotRecruitedNotNullAndNotRecruitedNot(String string);
	ArrayList<ApplicationProcess> findAllByIsExEmployee(boolean isExEployee);
}
