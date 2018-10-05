package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.repositories.SalaryPackageRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.ISalaryPackageService;

@Service
public class SalaryPackageService implements ISalaryPackageService {

	@Autowired
	private SalaryPackageRepository salaryPackageRepository;

	@Override
	public List<SalaryPackage> getAllSalaryPackages() {
		List<SalaryPackage> list = new ArrayList<>();
		salaryPackageRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public SalaryPackage getSalaryPackageById(int salaryPackageId) {
		SalaryPackage e = salaryPackageRepository.findById(salaryPackageId).get();
		return e;
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

	public void saveOrUpdateSalaryPackage(int id, SalaryPackage salaryPackage) {
		Optional<SalaryPackage> tmp = salaryPackageRepository.findById(id);
		if (tmp.isPresent()) {
			SalaryPackage s = tmp.get();

			s = salaryPackage;
			s.setId(id);
			salaryPackageRepository.save(s);
		} else {
			salaryPackageRepository.save(salaryPackage);
		}
	}

}
