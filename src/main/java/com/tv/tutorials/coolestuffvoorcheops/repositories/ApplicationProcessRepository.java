package com.tv.tutorials.coolestuffvoorcheops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.models.ApplicationProcess;

public interface ApplicationProcessRepository extends CrudRepository<ApplicationProcess, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String
	// postalCode);
	List<ApplicationProcess> findAllByIsRecruited(boolean isFrontend);

	List<ApplicationProcess> findAllBytoBeInvitedForFirstConversation(boolean isToBeInvitedForFirstConversation);

	// List<ApplicationProcess> findAllByIsRecruitedIn(List<ApplicationProcess>
	// list);

	List<ApplicationProcess> findAllByIsRecruitedIn(List<ApplicationProcess> t);
}
