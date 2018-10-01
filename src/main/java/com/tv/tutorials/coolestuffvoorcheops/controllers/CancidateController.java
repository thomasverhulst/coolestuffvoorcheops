package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tv.tutorials.coolestuffvoorcheops.model.Address;
import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.model.Update;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.AddressRepository;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.CandidateRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.AddressService;
import com.tv.tutorials.coolestuffvoorcheops.services.ApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.IStorageService;
import com.tv.tutorials.coolestuffvoorcheops.services.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.SkillsService;

@Controller
public class CancidateController {

	private final IStorageService storageService;
	public String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	// goede uitleg
	// https://www.mkyong.com/spring-boot/spring-boot-hibernate-search-example/
	@Autowired
	AddressService addressService;

	@Autowired
	CandidateService candidateservice;

	@Autowired
	SkillsService skillsService;

	@Autowired
	SalaryPackageService salaryPackageService;

	@Autowired
	ApplicationProcessService applicationProcessService;

	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
	AddressRepository addressRepository;

	@Autowired
	public CancidateController(IStorageService storageService) {
		this.storageService = storageService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/register")
	public String showRegister(ModelMap map) {
		// naar register.html, addres en candadate worden meegegeven
		// ModelMap
		// https://stackoverflow.com/questions/13242394/spring-mvc-multiple-modelattribute-on-the-same-form

		// map.addAttribute("files", storageService.loadAll().map(
		// path -> MvcUriComponentsBuilder.fromMethodName(FileUploaderController.class,
		// "serveFile", path.getFileName().toString()).build().toString())
		// .collect(Collectors.toList()));
		Update update = new Update(false);
		map.addAttribute("update", update);
		System.out.println("wat is dit " + update.isUpdate());
		map.addAttribute("address", new Address());
		map.addAttribute("candidate", new Candidate());
		return "register";
	}

	// terug knop, werkt niet
	@RequestMapping(value = "/registerCandidate", method = RequestMethod.POST, params = "action=back")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/registerCandidate", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("address") Address address,
			@ModelAttribute("candidate") Candidate candidate, HttpSession session) throws IOException {

		//
		session.setAttribute("isupdate", false);
		// Helper.em
		// save cv if it exists
		System.out.println("de cv link =" + candidate.getFile());
		if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
			// if ( candidate.getFile() != null) {
			MultipartFile file = candidate.getFile();
			Path filenameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			// System.out.println("Upload link = "+ uploadDirectory);
			Files.write(filenameAndPath, file.getBytes());
			//
			// set link to cv
			candidate.setCvLink(file.getOriginalFilename());
		}

		// https://stackoverflow.com/questions/2227395/spring-3-0-set-and-get-session-attribute
		// save candidate to get an id
		Candidate tmpCandidate = candidateservice.addCandidate(candidate);
		session.setAttribute("candidate", tmpCandidate);

		// adres
		Address tmpAddress = addressService.addAddress(address);

		candidate.setAddressId(tmpAddress.getId());
		candidateservice.updateCandidate(candidate);
		System.out.println("we hebben iest weggeschreven");
		System.out.println("Address saven is gelukt? ");

		System.out.println("Kandidaat id " + tmpCandidate.getId());
		model.addAttribute("skills", new Skills());
		return "skills";
	}

	@PostMapping(value = "/updateCandidate")
	public String updateArticle(Candidate candidate, Address address) {
		addressService.updateAddress(address);
		System.out.println("we zijn in deput");
		// candidate.setAddressId(tmpAddress.getId());
		candidateservice.updateCandidate(candidate);
		return "test";
	}

	@RequestMapping(value = "/updateCandidate2", method = RequestMethod.POST)
	public String updateCandidate(Model model, @ModelAttribute("address") Address address,
			@ModelAttribute("candidate") Candidate candidate, HttpSession session) throws IOException {

		// save cv
		System.out.println(candidate.getId());
		if (candidate.getFile() != null) {
			MultipartFile file = candidate.getFile();
			Path filenameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			// System.out.println("Upload link = "+ uploadDirectory);
			Files.write(filenameAndPath, file.getBytes());
			//
			// set link to cv
			candidate.setCvLink(file.getOriginalFilename());
		}
		System.out.println("we zijnn in de updatecontroller");

		// https://stackoverflow.com/questions/2227395/spring-3-0-set-and-get-session-attribute
		// save candidate to get an id
		// Candidate tmpCandidate =candidateservice.addCandidate(candidate);
		session.setAttribute("candidate", candidate);

		// wat als er nog geen adresid is?
		addressService.updateAddress(address);

		// candidate.setAddressId(tmpAddress.getId());
		candidateservice.updateCandidate(candidate);

		return "test";
	}

	@RequestMapping(value = "/registerSkills", method = RequestMethod.POST)
	public String registerSkills(Model model, @ModelAttribute("skills") Skills skills, HttpSession session) {
		// voorlopig terug naar dde testpagina

		// SkillsService ms = new SkillsService();
		Skills tmpSkills = skillsService.addSkills(skills);
		System.out.println("Skills id = " + tmpSkills.getId());

		// get candidate from session
		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
		// set SkillsId to candidate from session
		sessionCandidate.setSkillsId(tmpSkills.getId());
		// Update candidate in database
		candidateservice.updateCandidate(sessionCandidate);
		// Update candidate in session (not needed?)
		session.setAttribute("candidate", sessionCandidate);
		model.addAttribute("salarypackage", new SalaryPackage());
		return "salarypackage";
	}

	@RequestMapping(value = "/registerSalaryPackage", method = RequestMethod.POST)
	public String registerSalaryPackage(Model model, @ModelAttribute("salarypackage") SalaryPackage salaryPackage,
			HttpSession session) {
		System.out.println("we passeren bij salary");
		System.out.println("we passeren hier " + salaryPackage.getGrossSalary());
		String returning = "applicationProcess";
		// if voor als het een update is
		boolean update = (boolean) session.getAttribute("isupdate");
		if (update) {
			System.out.println("we zijn een salarypackage aan het updaten");
			int salarypackageIdentificator = (int) session
					.getAttribute("currentSalaryPackage0OrProposedSalarypackage1");
			SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);
			Candidate sessionUpdateCandidate = (Candidate) session.getAttribute("candidate");
			if (salarypackageIdentificator == 0) {
				sessionUpdateCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
				System.out.println("current geupdated");

			} else {
				sessionUpdateCandidate.setProposedSallaryPackageId(tmpSalarypackage.getId());
				System.out.println("proposed geupdated");
			}
			candidateservice.updateCandidate(sessionUpdateCandidate);
			returning = "updatesucces";
		} else {// binnen deze else is het de gewone flow bij het aanmalkn van een kandidaat

			// Candidate tmpCandidate =candidateservice.addCandidate(salarypackage);
			SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);

			// get candidate from session
			Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
			// set SkillsId to candidate from session
			sessionCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
			// Update candidate in database
			candidateservice.updateCandidate(sessionCandidate);
			// Update candidate in session (not needed?)
			session.setAttribute("candidate", sessionCandidate);
			System.out.println("salary id = " + tmpSalarypackage.getId());
			model.addAttribute("applicationprocess", new ApplicationProcess());
		}
		return returning;
	}

	@RequestMapping(value = "/registerApplicationProcess", method = RequestMethod.POST)
	public String registerApplicationProcess(Model model,
			@ModelAttribute("applicationprocess") ApplicationProcess applicationProcess, HttpSession session) {
		System.out.println("we passeren bij applicatiprocess");
		System.out.println("we passeren bij applicatiprocess " + applicationProcess.getFeedbackFinancialProposal());

		ApplicationProcess tmpApplicationprocess = applicationProcessService.addApplicationProcess(applicationProcess);
		// get candidate from session
		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
		// set SkillsId to candidate from session
		sessionCandidate.setApplicationProcessId(tmpApplicationprocess.getId());
		// Update candidate in database
		candidateservice.updateCandidate(sessionCandidate);
		// Update candidate in session (not needed?)
		session.setAttribute("candidate", sessionCandidate);

		System.out.println("applicationprocess Id  = " + tmpApplicationprocess.getId());
		Boolean t = (Boolean) session.getAttribute("isupdate");
		System.out.println("waarde" + t);
		String returning;

		if (t == false) {
			// de eigelijke update moet hier nog gebeuren, uiteindelijk moet dit ook naar
			// een service?

			returning = "registrationsucces";
		} else {
			returning = "updatesucces";
		}

		return returning;
	}

	// naar voorstel
	@RequestMapping(value = "/registerSalaryPackageProposal")
	public String registerSalaryPackageProposal(Model model) {
		// voorlopig terug naar dde testpagina

		System.out.println("we passeren bij salarypackageProposal");
		model.addAttribute("salarypackage", new SalaryPackage());

		return "salarypackageproposal";
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public String sendMail(Model model, @ModelAttribute("salarypackage") SalaryPackage salaryPackage) {
		// voorlopig terug naar dde testpagina

		System.out.println("we passeren bij salarypackageProposal");
		System.out.println("we passeren hier " + salaryPackage.getGrossSalary());

		// model.addAttribute("salarypackage", new SalaryPackage());

		return "mailsucces";
	}

	@PostMapping("/updateCandidate/{candidateId}/{addressId}")
	public String updateCondidate(@Valid Candidate candidate, @Valid Address address,
			@PathVariable("candidateId") int id, @PathVariable("addressId") int addressId, BindingResult result,
			RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {

			return "updateCandidate";
		}
		System.out.println("we komen hierlangs");
		// de MULTIPARTFILE WORDT HIER NOG NIET OPGESLAGEN

		if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
			// if ( candidate.getFile() != null) {
			MultipartFile file = candidate.getFile();
			Path filenameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			// System.out.println("Upload link = "+ uploadDirectory);
			Files.write(filenameAndPath, file.getBytes());
			// set link to cv
			candidate.setCvLink(file.getOriginalFilename());
		}

		candidateservice.saveOrUpdateCandidate(id, candidate, address, addressId);
		redirect.addFlashAttribute("success", "Saved employee successfully!");
		return "updatesucces";

	}

	@GetMapping("/searchcv2/{candidateId}")
	public String searchCv2(Model model, @Valid Candidate candidate, @Valid Address address,
			@PathVariable("candidateId") int id, BindingResult result, RedirectAttributes redirect,
			HttpServletResponse response) throws IOException {

		String returnValue = "toupdatecv";
		String cvLink = "";
		Optional<Candidate> tmp = candidateRepository.findById(id);

		// de kandidaat bestaat
		if (tmp.isPresent()) {
			// de kandidaat heeft een cv (of moet je kijken naar cv link?
			if (tmp.get().getFile() != null && !tmp.get().getFile().isEmpty()) {
				// cv wordt gedownloaded
				System.out.println("het cv bestaat");
				candidateservice.downloadCv(tmp.get().getCvLink(), response);
				// updatesucess is raar, want wordt niet geupdated
				returnValue = "updatesucess";

			}
			System.out.println("file is null?");
		}

		model.addAttribute("candidate", tmp.get());
		return returnValue;
	}
	// searchAllCandidatesWithActiveApplicationProcess

}
