package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.repositories.SalaryPackageRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.ISalaryPackageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalaryPackageServiceTest {

	@Autowired
	private ISalaryPackageService salaryPackageService;
	@Autowired
	private SalaryPackageRepository salaryPackageRepo;

	@Before
	public void fillDb() {
		SalaryPackage salPack1 = new SalaryPackage(2500.0, "BMW 1", 40.0, 40.0, true, true);
		SalaryPackage salPack2 = new SalaryPackage(3500.0, "BMW 3", 40.0, 40.0, true, true);
		SalaryPackage salPack3 = new SalaryPackage(4500.0, "BMW 5", 40.0, 40.0, true, true);

		salaryPackageService.addSalaryPackage(salPack1);
		salaryPackageService.addSalaryPackage(salPack2);
		salaryPackageService.addSalaryPackage(salPack3);
	}

	@After
	public void flushDb() {
		salaryPackageRepo.deleteAll();
	}

	@Test
	public void getAllSalaryPackagesTest() {
		List<SalaryPackage> salaryPackages = salaryPackageService.getAllSalaryPackages();
		assertThat(salaryPackages).isNotEmpty();
	}

	@Test
	public void getSalaryPackageByIdTest() {
		SalaryPackage salPackage = salaryPackageService
				.getSalaryPackageById(salaryPackageService.getAllSalaryPackages().get(0).getId());
		assertThat(salPackage).isNotNull();
	}

	@Test
	public void addSalaryPackageTest() {
		SalaryPackage salPack = new SalaryPackage(7500.0, "BMW 1", 40.0, 40.0, true, true);
		salPack = salaryPackageService.addSalaryPackage(salPack);
		assertThat(salPack.getId()).isNotEqualTo(0);
	}

	@Test
	public void updateSalaryPackageTest() {
		SalaryPackage salPack = new SalaryPackage(7500.0, "Tesla", 40.0, 40.0, true, true);
		salPack = salaryPackageService.addSalaryPackage(salPack);
		salPack.setCar("Dacia");
		salaryPackageService.updateSalaryPackage(salPack);
		assertThat(salaryPackageService.getSalaryPackageById(salPack.getId()).getCar()).isEqualTo("Dacia");
	}

	@Test
	public void deleteSalaryPackage() {
		Integer count = salaryPackageService.getAllSalaryPackages().size();
		salaryPackageService.deleteSalaryPackage(salaryPackageService.getAllSalaryPackages().get(0).getId());
		assertThat(salaryPackageService.getAllSalaryPackages().size()).isEqualTo(--count);
	}

}
