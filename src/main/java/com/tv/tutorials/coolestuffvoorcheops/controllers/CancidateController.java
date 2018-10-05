package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import org.thymeleaf.util.StringUtils;

import com.tv.tutorials.coolestuffvoorcheops.models.Address;
import com.tv.tutorials.coolestuffvoorcheops.models.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.models.Update;
import com.tv.tutorials.coolestuffvoorcheops.repositories.CandidateRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.IStorageService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.AddressService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.ApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SkillsService;

@Controller
public class CancidateController {

	Logger logger = Logger.getLogger(CancidateController.class);
	private final IStorageService storageService;
	public String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	// goede uitleg
	// https://www.mkyong.com/spring-boot/spring-boot-hibernate-search-example/
	@Autowired
	private AddressService addressService;

	@Autowired
	private CandidateService candidateservice;

	@Autowired
	private SkillsService skillsService;

	@Autowired
	private SalaryPackageService salaryPackageService;

	@Autowired
	private ApplicationProcessService applicationProcessService;

	@Autowired
	private CandidateRepository candidateRepository;

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

		Update update = new Update(false);
		map.addAttribute("update", update);
		map.addAttribute("address", new Address());
		map.addAttribute("candidate", new Candidate());
		return "register";
	}

	// terug knop, werkt niet
	@RequestMapping(value = "/registerCandidate", method = RequestMethod.POST, params = "action=back")
	public String home() {
		return "index";
	}

	@GetMapping("/header")
	public String getHeader() {
		return "header.html";
	}

	@RequestMapping(value = "/registerCandidate", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("address") Address address,
			@ModelAttribute("candidate") Candidate candidate, HttpSession session) throws IOException {
		
		session.setAttribute("isupdate", false);
		
		// save cv if it exists
		logger.debug("de cv link =" + candidate.getFile());
		if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
			
			MultipartFile file = candidate.getFile();
			Path filenameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

			logger.debug("Upload link = " + uploadDirectory);
			logger.debug("filenaam pad" + filenameAndPath);
			// Files.write(filenameAndPath, file.getBytes());
			Files.write(filenameAndPath, file.getBytes());
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

		logger.debug("Kandidaat id " + tmpCandidate.getId());
		model.addAttribute("skills", new Skills());
		return "skills";
	}

	@PostMapping(value = "/updateCandidate")
	public String updateArticle(Candidate candidate, Address address) {
		addressService.updateAddress(address);
		candidateservice.updateCandidate(candidate);
		return "test";
	}

	@RequestMapping(value = "/updateCandidate2", method = RequestMethod.POST)
	public String updateCandidate(Model model, @ModelAttribute("address") Address address,
			@ModelAttribute("candidate") Candidate candidate, HttpSession session) throws IOException {

		// save cv
		if (candidate.getFile() != null) {
			MultipartFile file = candidate.getFile();
			Path filenameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			Files.write(filenameAndPath, file.getBytes());
			candidate.setCvLink(file.getOriginalFilename());
		}

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
		//check if it is an update (late adding ) of skills
		boolean update = (boolean) session.getAttribute("isupdate");
		// get candidate from session
		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");

		if (update) {
			Skills tmpSkills = skillsService.addSkills(skills);
			//SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);
			//Candidate sessionUpdateCandidate = (Candidate) session.getAttribute("candidate");
			skillsService.addSkills(tmpSkills); 
			sessionCandidate.setSkillsId(tmpSkills.getId());
			//sessionUpdateCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
			candidateservice.updateCandidate(sessionCandidate);
			return  "updatesucces";
		}
		Skills tmpSkills = skillsService.addSkills(skills);
		logger.debug("Skills id = " + tmpSkills.getId());

		
		//Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
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
		logger.debug("we passeren hier " + salaryPackage.getGrossSalary());
		String returning = "applicationProcess";
		// if voor als het een update is
		boolean update = (boolean) session.getAttribute("isupdate");
		if (update) {
			int salarypackageIdentificator = (int) session
					.getAttribute("currentSalaryPackage0OrProposedSalarypackage1");
			SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);
			Candidate sessionUpdateCandidate = (Candidate) session.getAttribute("candidate");
			if (salarypackageIdentificator == 0) {
				sessionUpdateCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
				logger.debug("current geupdated");

			} else {
				sessionUpdateCandidate.setProposedSallaryPackageId(tmpSalarypackage.getId());
				logger.debug("proposed geupdated");
			}
			candidateservice.updateCandidate(sessionUpdateCandidate);
			returning = "updatesucces";
		} else {

			SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);

			// get candidate from session
			Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
			// set SalarypackageId to candidate from session
			sessionCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
			// Update candidate in database
			candidateservice.updateCandidate(sessionCandidate);
			// Update candidate in session (not needed?)
			session.setAttribute("candidate", sessionCandidate);
			model.addAttribute("applicationprocess", new ApplicationProcess());
		}
		return returning;
	}

	@RequestMapping(value = "/registerApplicationProcess", method = RequestMethod.POST)
	public String registerApplicationProcess(Model model,
			@ModelAttribute("applicationprocess") ApplicationProcess applicationProcess, HttpSession session) {
		ApplicationProcess tmpApplicationprocess = applicationProcessService.addApplicationProcess(applicationProcess);
		// get candidate from session
		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
		// set ApplicationprocessId to candidate from session
		sessionCandidate.setApplicationProcessId(tmpApplicationprocess.getId());
		// Update candidate in database
		candidateservice.updateCandidate(sessionCandidate);
		// Update candidate in session (not needed?)
		session.setAttribute("candidate", sessionCandidate);
		Boolean t = (Boolean) session.getAttribute("isupdate");
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
		model.addAttribute("salarypackage", new SalaryPackage());

		return "salarypackageproposal";
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public String sendMail(Model model, @ModelAttribute("salarypackage") SalaryPackage salaryPackage) {
		return "mailsucces";
	}

	@PostMapping("/updateCandidate/{candidateId}/{addressId}")
	public String updateCondidate(@Valid Candidate candidate, @Valid Address address,
			@PathVariable("candidateId") int id, @PathVariable("addressId") int addressId, BindingResult result,
			RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {

			return "updateCandidate";
		}
		// de MULTIPARTFILE WORDT HIER NOG NIET OPGESLAGEN

		if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
			// if ( candidate.getFile() != null) {
			MultipartFile file = candidate.getFile();
			Path filenameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
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
		Optional<Candidate> tmp = candidateRepository.findById(id);

		// de kandidaat bestaat
		if (tmp.isPresent()) {
			logger.debug("het cv bestaat");
			if (!StringUtils.isEmpty(tmp.get().getCvLink())) {
				candidateservice.downloadCv(tmp.get().getCvLink(), response);
			}
		}

		model.addAttribute("candidate", tmp.get());
		return returnValue;
	}

}
