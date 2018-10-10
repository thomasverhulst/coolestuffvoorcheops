package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.repositories.ApplicationProcessRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationProcessServiceTest {

	@Autowired
	private ApplicationProcessService applicationService;

	@Autowired
	private ApplicationProcessRepository applicationProcessRepo;

	@Test
	public void getAllApplicationProcessesTest() {
		assertThat(applicationService.getAllApplicationProcesses()).isNotEmpty();
	}

	@Test
	public void getSalaryPackageByIdTest() {
		ApplicationProcess applicationPackage = applicationService
				.getApplicationProcessById(applicationService.getAllApplicationProcesses().get(0).getId());
		assertThat(applicationPackage).isNotNull();
	}

	@Test
	public void addSalaryPackageByIdTest() {
		ApplicationProcess appProcessTest = new ApplicationProcess(Date.from(Instant.now()), true,
				Date.from(Instant.now()), null, "Kristel", "Goed", true, Date.from(Instant.now()), "", "", false, null,
				"", false);

		appProcessTest = applicationService.addApplicationProcess(appProcessTest);
		assertThat(appProcessTest.getId()).isNotEqualTo(0);
	}

	@Test
	public void deleteSalaryPackageTest() {
		Integer count = applicationService.getAllApplicationProcesses().size();
		ApplicationProcess appProcessTest = new ApplicationProcess(Date.from(Instant.now()), true,
				Date.from(Instant.now()), null, "vvv", "Goed", true, Date.from(Instant.now()), "", "", false, null, "",
				false);
		appProcessTest = applicationService.addApplicationProcess(appProcessTest);
		applicationService.deleteApplicationProcess(appProcessTest.getId());
		assertThat(applicationService.getAllApplicationProcesses().size()).isEqualTo(count);

	}

	@Test
	public void updateSalaryPackageTest() {
		ApplicationProcess appProcess = applicationService.getAllApplicationProcesses().get(0);
		appProcess.setStaffNameFirstConversation("Yannick");
		applicationService.updateApplicationProcess(appProcess);
		assertThat(appProcess.getStaffNameFirstConversation()).isEqualToIgnoringCase("Yannick");
	}

	@Before
	public void fillDb() {

		// ApplicationProcess
		ApplicationProcess appProcessHenkie = new ApplicationProcess(Date.from(Instant.now()), true,
				Date.from(Instant.now()), null, "Alain", "Goed", true, Date.from(Instant.now()), "", "", false, null,
				"", false);
		ApplicationProcess appProcessYannick = new ApplicationProcess(Date.from(Instant.now()), true,
				Date.from(Instant.now()), null, "Alain", "Goed", true, Date.from(Instant.now()), "", "", true, null, "",
				true);

		ApplicationProcess appProcessVvv = new ApplicationProcess(null, false, null, null, "Alain", "Goed", false, null,
				"", "", false, null, "", true);

		appProcessHenkie = applicationService.addApplicationProcess(appProcessHenkie);
		appProcessYannick = applicationService.addApplicationProcess(appProcessYannick);
		appProcessVvv = applicationService.addApplicationProcess(appProcessVvv);

	}

	@After
	public void flushDb() {
		applicationProcessRepo.deleteAll();
	}
}
