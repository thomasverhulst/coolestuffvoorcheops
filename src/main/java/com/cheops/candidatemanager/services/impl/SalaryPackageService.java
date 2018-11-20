package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.cheops.candidatemanager.models.SalaryPackage;
import com.cheops.candidatemanager.repositories.SalaryPackageRepository;
import com.cheops.candidatemanager.services.ISalaryPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryPackageService implements ISalaryPackageService {

	@Autowired
	private SalaryPackageRepository salaryPackageRepository;

	@Override
	public List<SalaryPackage> getAllSalaryPackages() {
		List<SalaryPackage> list = new ArrayList<>();
		salaryPackageRepository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public SalaryPackage getSalaryPackageById(int salaryPackageId) {
		return  salaryPackageRepository.findById(salaryPackageId).orElse(null);
	}

	@Override
	public SalaryPackage addSalaryPackage(SalaryPackage salaryPackage) {
		return salaryPackageRepository.save(salaryPackage);
	}

	@Override
	public void updateSalaryPackage(SalaryPackage salaryPackage) {
		salaryPackageRepository.save(salaryPackage);
	}

	@Override
	public void deleteSalaryPackage(int salaryPackageId) {
		salaryPackageRepository.delete(getSalaryPackageById(salaryPackageId));
	}

}
