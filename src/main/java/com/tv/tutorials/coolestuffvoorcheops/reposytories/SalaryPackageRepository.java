package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;

public interface SalaryPackageRepository extends CrudRepository<SalaryPackage, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String
	// postalCode);

}
