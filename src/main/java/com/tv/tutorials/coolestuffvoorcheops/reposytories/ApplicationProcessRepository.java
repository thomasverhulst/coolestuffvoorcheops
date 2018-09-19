package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;

public interface ApplicationProcessRepository extends  CrudRepository<ApplicationProcess, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode);
	List<ApplicationProcess> findAllByIsRecruited(boolean isFrontend);
}
