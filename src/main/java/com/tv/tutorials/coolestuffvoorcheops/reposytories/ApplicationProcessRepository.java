package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;

public interface ApplicationProcessRepository extends  CrudRepository<ApplicationProcess, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode);

}
