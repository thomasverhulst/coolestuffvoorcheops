package com.tv.tutorials.coolestuffvoorcheops.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage;

public interface SalaryPackageRepository extends CrudRepository<SalaryPackage, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String
	// postalCode);

}
