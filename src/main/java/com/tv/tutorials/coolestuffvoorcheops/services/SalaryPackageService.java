package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.SalaryPackageRepository;

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

//	@Override
//	public Skills getSkillsById(int skillsId) {
//		Skills e = skillsRepository.findById(skillsId).get();
//		return e;
//	}
	@Override
	public SalaryPackage getSalaryPackageById(int salaryPackageId) {
		//salaryPackageRepository.findById(salaryPackageId).
		System.out.println("id "+salaryPackageId);
		SalaryPackage e = salaryPackageRepository.findById(salaryPackageId).get();
		System.out.println( "id is  = "+e.getId());
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
	
}
