package com.cheops.candidatemanager.repositories;

import java.util.List;

import com.cheops.candidatemanager.models.ApplicationProcess;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationProcessRepository extends CrudRepository<ApplicationProcess, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String

	List<ApplicationProcess> findAllByIsRecruited(boolean isFrontend);

	List<ApplicationProcess> findAllBytoBeInvitedForFirstConversation(boolean isToBeInvitedForFirstConversation);

	// List<ApplicationProcess> findAllByIsRecruitedIn(List<ApplicationProcess>
	List<ApplicationProcess> findAllByIsRecruitedIn(List<ApplicationProcess> t);
}
