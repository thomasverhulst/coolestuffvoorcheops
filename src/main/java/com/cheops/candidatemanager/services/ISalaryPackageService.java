package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.SalaryPackage;

public interface ISalaryPackageService {

	List<SalaryPackage> getAllSalaryPackages();

	SalaryPackage getSalaryPackageById(int salaryPackageId);

	SalaryPackage addSalaryPackage(SalaryPackage salaryPackage);

	void updateSalaryPackage(SalaryPackage salaryPackage);

	void deleteSalaryPackage(int salaryPackageId);
}