package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;

public interface ISalaryPackageService {

	List<SalaryPackage> getAllSalaryPackages();

	SalaryPackage getSalaryPackageById(int salaryPackageId);

	SalaryPackage addSalaryPackage(SalaryPackage salaryPackage);

	void updateSalaryPackage(SalaryPackage salaryPackage);

	void deleteSalaryPackage(int salaryPackageId);
}