package com.cheops.candidatemanager.controllers;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cheops.candidatemanager.exceptions.*;
import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.services.ICandidateServiceFE;
import com.cheops.candidatemanager.services.ICountryService;
import com.cheops.candidatemanager.services.ISalaryPackageService;
import com.cheops.candidatemanager.services.ITechnologyService;
import com.cheops.candidatemanager.services.impl.FileStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes({CandidateController.CANDIDATE})
public class CandidateController {

  static final String CANDIDATE = "candidate";

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private FileStorageService fileStorageService;

  @Autowired
  private ICandidateServiceFE newCandidateServiceFE;

  @Autowired
  private ICountryService countryService;

  @Autowired
  private ITechnologyService technologyService;





//	Logger logger = Logger.getLogger(CancidateController.class);

//	private final IStorageService storageService;
	
//	private static final  String FEEDBACKUPLOADDIRECTORY = System.getProperty("user.dir") + "/uploadsfeedback";
	
	// goede uitleg
	// https://www.mkyong.com/spring-boot/spring-boot-hibernate-search-example/
//	@Autowired
//	private AddressService addressService;
//
//	@Autowired
//	private CandidateService candidateservice;
//
//	@Autowired
//	private NewCandidateService newCandidateservice;



//	@Autowired
//	private SkillsService skillsService;
//
//	@Autowired
//	private SalaryPackageService salaryPackageService;
//
//	@Autowired
//	private ApplicationProcessService applicationProcessService;
//
//	@Autowired
//	private CandidateRepository candidateRepository;


//


//	@Autowired
//	public CancidateController(IStorageService storageService) {
//		this.storageService = storageService;
//	}

//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/register")
//	public String showRegister(ModelMap map) {
//		// naar register.html, addres en candadate worden meegegeven
//		// ModelMap
//		// https://stackoverflow.com/questions/13242394/spring-mvc-multiple-modelattribute-on-the-same-form
//
//		Update update = new Update(false);
//		map.addAttribute("update", update);
//		map.addAttribute("address", new Address());
//		map.addAttribute("candidate", new Candidate());
//		return "register";
//	}
//
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/showRegisterNew")
//	public String showRegisterNew(ModelMap map) {
//		// naar register.html, addres en candadate worden meegegeven
//		// ModelMap
//		// https://stackoverflow.com/questions/13242394/spring-mvc-multiple-modelattribute-on-the-same-form
//
//		Update update = new Update(false);
//		map.addAttribute("update", update);
//		map.addAttribute("candidate", new NewCandidate());
//		return "registernew";
//	}
//
//	// terug knop, werkt niet
//	@PostMapping(value = "/registerCandidate", params = "action=back")
//	public String home() {
//		return "index";
//	}
//
//	@GetMapping("/header")
//	public String getHeader() {
//		return "header.html";
//	}
//
//	@PostMapping(value = "/registerCandidate")
//	public String register(Model model, @ModelAttribute("address") Address address,
//			@ModelAttribute("candidate") Candidate candidate, HttpSession session) throws IOException {
//
//		session.setAttribute("isupdate", false);
//
//
//		//set timestamp when can,didate is added
//		Timestamp isAddedTimeStamp = new Timestamp(System.currentTimeMillis());
//		candidate.setIsAddedTimeStamp(isAddedTimeStamp);
//
//		// save cv if it exists
//		logger.debug("de cv link =" + candidate.getFile());
//		if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
//
//			MultipartFile file = candidate.getFile();
//			Path filenameAndPath = Paths.get(UPLOADDIRECTORY, file.getOriginalFilename());
//
//			logger.debug("Upload link = " + UPLOADDIRECTORY);
//			logger.debug("filenaam pad" + filenameAndPath);
//			Files.write(filenameAndPath, file.getBytes());
//			// set link to cv
//			candidate.setCvLink(file.getOriginalFilename());
//		}
//
//		// https://stackoverflow.com/questions/2227395/spring-3-0-set-and-get-session-attribute
//		// save candidate to get an id
//		Candidate tmpCandidate = candidateservice.addCandidate(candidate);
//		session.setAttribute("candidate", tmpCandidate);
//
//		// adres
//		Address tmpAddress = addressService.addAddress(address);
//
//		candidate.setAddressId(tmpAddress.getId());
//		candidateservice.updateCandidate(candidate);
//
//		logger.debug("Kandidaat id " + tmpCandidate.getId());
//		model.addAttribute("skills", new Skill());
//		return "skills";
//	}
//
//	@PostMapping(value = "/registerCandidateNew")
//	public String registerNew(Model model, @ModelAttribute("address") Address address,
//			@ModelAttribute("candidate") NewCandidate newCandidate, HttpSession session) throws IOException {
//
//		session.setAttribute("isupdate", false);
//
//		//set timestamp when can,didate is added
//		Timestamp isAddedTimeStamp = new Timestamp(System.currentTimeMillis());
//		newCandidate.setIsAddedTimeStamp(isAddedTimeStamp);
//
//		//check if isrecruited is set, if so, add timestamp
//		if(newCandidate.getApplicationProcess().getIsRecruited()) {
//			Timestamp isRecruitedTimeStamp = new Timestamp(System.currentTimeMillis());
//			newCandidate.getApplicationProcess().setIsRecruitedTimeStamp(isRecruitedTimeStamp);
//		}
//
//		//check if isExemployee is set, if so, add timestamp and unset "isrecruited
//		if(newCandidate.getApplicationProcess().getIsExEmployee()) {
//			Timestamp isExEmployeeTimeStamp = new Timestamp(System.currentTimeMillis());
//			newCandidate.getApplicationProcess().setIsExEmployeeTimeStamp(isExEmployeeTimeStamp);
//			newCandidate.getApplicationProcess().setIsRecruited(false);
//		}
//
//		// save cv if it exists
//		logger.debug("de cv link =" + newCandidate.getFile());
//		if (newCandidate.getFile() != null && !newCandidate.getFile().isEmpty()) {
//
//			MultipartFile file = newCandidate.getFile();
//			Path filenameAndPath = Paths.get(UPLOADDIRECTORY, file.getOriginalFilename());
//
//			logger.debug("Upload link = " + UPLOADDIRECTORY);
//			logger.debug("filenaam pad" + filenameAndPath);
//			Files.write(filenameAndPath, file.getBytes());
//			// set link to cv
//			newCandidate.setCvLink(file.getOriginalFilename());
//		}
//
//		if (newCandidate.getApplicationProcess().getFile() != null && !newCandidate.getApplicationProcess().getFile().isEmpty()) {
//
//			MultipartFile file = newCandidate.getApplicationProcess().getFile();
//
//			Path filenameAndPath = Paths.get(FEEDBACKUPLOADDIRECTORY, file.getOriginalFilename());
//			Files.write(filenameAndPath, file.getBytes());
//			// set link to cv
//			newCandidate.getApplicationProcess().setFeedbackFileName(file.getOriginalFilename());
//
//		}
//
//
//		// https://stackoverflow.com/questions/2227395/spring-3-0-set-and-get-session-attribute
//		// save candidate to get an id
//		NewCandidate tmpNewCandidate = newCandidateservice.addNewCandidate(newCandidate);
//
//		tmpNewCandidate.setAddressId(tmpNewCandidate.getAddress().getId() );
//		tmpNewCandidate.setApplicationProcessId(tmpNewCandidate.getApplicationProcess().getId() );
//		tmpNewCandidate.setSkillsId(tmpNewCandidate.getSkill().getId());
//
//		newCandidateservice.updateNewCandidate(tmpNewCandidate);
//		session.setAttribute("candidate", tmpNewCandidate);
//
//
//		logger.debug("Kandidaat id " + tmpNewCandidate.getId());
//
//		return "updatesucces";
//	}
//
//
//	@PostMapping(value = "/updateCandidate")
//	public String updateArticle(Candidate candidate, Address address) {
//		addressService.updateAddress(address);
//		candidateservice.updateCandidate(candidate);
//		return "test";
//	}
//
//	@PostMapping(value = "/updateCandidate2")
//	public String updateCandidate(Model model, @ModelAttribute("address") Address address,
//			@ModelAttribute("candidate") Candidate candidate, HttpSession session) throws IOException {
//
//		// save cv
//		if (candidate.getFile() != null) {
//			MultipartFile file = candidate.getFile();
//			Path filenameAndPath = Paths.get(UPLOADDIRECTORY, file.getOriginalFilename());
//			Files.write(filenameAndPath, file.getBytes());
//			candidate.setCvLink(file.getOriginalFilename());
//		}
//
//		// https://stackoverflow.com/questions/2227395/spring-3-0-set-and-get-session-attribute
//		// save candidate to get an id
//		session.setAttribute("candidate", candidate);
//
//		// wat als er nog geen adresid is?
//		addressService.updateAddress(address);
//
//		candidateservice.updateCandidate(candidate);
//
//		return "test";
//	}
//
//	@PostMapping(value = "/registerSkills")
//	public String registerSkills(Model model, @ModelAttribute("skills") Skill skills, HttpSession session) {
//		// check if it is an update (late adding ) of skills
//		boolean update = (boolean) session.getAttribute("isupdate");
//		// get candidate from session
//		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
//
//		if (update) {
//			Skill tmpSkills = skillsService.addSkills(skills);
//			skillsService.addSkills(tmpSkills);
//			sessionCandidate.setSkillsId(tmpSkills.getId());
//			candidateservice.updateCandidate(sessionCandidate);
//			return "updatesucces";
//		}
//		Skill tmpSkills = skillsService.addSkills(skills);
//		logger.debug("Skill id = " + tmpSkills.getId());
//
//		// set SkillsId to candidate from session
//		sessionCandidate.setSkillsId(tmpSkills.getId());
//		// Update candidate in database
//		candidateservice.updateCandidate(sessionCandidate);
//		// Update candidate in session (not needed?)
//		session.setAttribute("candidate", sessionCandidate);
//		model.addAttribute("salarypackage", new SalaryPackage());
//		return "salarypackage";
//	}
//
//	@PostMapping(value = "/registerSalaryPackage")
//	public String registerSalaryPackage(Model model, @ModelAttribute("salarypackage") SalaryPackage salaryPackage,
//			HttpSession session) {
//		logger.debug("we passeren hier " + salaryPackage.getGrossSalary());
//		String returning = "applicationProcess";
//		// if voor als het een update is
//		boolean update = (boolean) session.getAttribute("isupdate");
//		if (update) {
//			int salarypackageIdentificator = (int) session
//					.getAttribute("currentSalaryPackage0OrProposedSalarypackage1");
//			SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);
//			Candidate sessionUpdateCandidate = (Candidate) session.getAttribute("candidate");
//			if (salarypackageIdentificator == 0) {
//				sessionUpdateCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
//				logger.debug("current geupdated");
//
//			} else {
//				sessionUpdateCandidate.setProposedSallaryPackageId(tmpSalarypackage.getId());
//				logger.debug("proposed geupdated");
//			}
//			candidateservice.updateCandidate(sessionUpdateCandidate);
//			returning = "updatesucces";
//		} else {
//
//			SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);
//
//			// get candidate from session
//			Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
//			// set SalarypackageId to candidate from session
//			sessionCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
//			// Update candidate in database
//			candidateservice.updateCandidate(sessionCandidate);
//			// Update candidate in session (not needed?)
//			session.setAttribute("candidate", sessionCandidate);
//			model.addAttribute("applicationprocess", new ApplicationProcess());
//		}
//		return returning;
//	}
//
//	@PostMapping(value = "/registerApplicationProcess")
//	public String registerApplicationProcess(Model model,
//			@ModelAttribute("applicationprocess") ApplicationProcess applicationProcess, BindingResult bindingResult, HttpSession session) throws IOException {
//
//		//check if isrecruited is set, if so, add timestamp
//		if(applicationProcess.getIsRecruited()) {
//			Timestamp isRecruitedTimeStamp = new Timestamp(System.currentTimeMillis());
//			applicationProcess.setIsRecruitedTimeStamp(isRecruitedTimeStamp);
//		}
//
//		//check if feedbackfile is added
//		if (applicationProcess.getFile() != null && !applicationProcess.getFile().isEmpty()) {
//			MultipartFile file = applicationProcess.getFile();
//
//			Path filenameAndPath = Paths.get(FEEDBACKUPLOADDIRECTORY, file.getOriginalFilename());
//			Files.write(filenameAndPath, file.getBytes());
//			// set link to cv
//			applicationProcess.setFeedbackFileName(file.getOriginalFilename());
//
//		}
//
//		ApplicationProcess tmpApplicationprocess = applicationProcessService.addApplicationProcess(applicationProcess);
//		// get candidate from session
//		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
//		// set ApplicationprocessId to candidate from session
//		sessionCandidate.setApplicationProcessId(tmpApplicationprocess.getId());
//		// Update candidate in database
//		candidateservice.updateCandidate(sessionCandidate);
//		// Update candidate in session (not needed?)
//		session.setAttribute("candidate", sessionCandidate);
//		Boolean isUpdate = (Boolean) session.getAttribute("isupdate");
//		String returning;
//
//		if (!isUpdate) {
//			// de eigelijke update moet hier nog gebeuren, uiteindelijk moet dit ook naar
//			// een service?
//
//			returning = "registrationsucces";
//		} else {
//			returning = "updatesucces";
//		}
//
//		return returning;
//	}
//
//	// naar voorstel
//	@RequestMapping(value = "/registerSalaryPackageProposal")
//	public String registerSalaryPackageProposal(Model model) {
//		// voorlopig terug naar dde testpagina
//		model.addAttribute("salarypackage", new SalaryPackage());
//
//		return "salarypackageproposal";
//	}
//
//	@PostMapping(value = "/sendmail")
//	public String sendMail(Model model, @ModelAttribute("salarypackage") SalaryPackage salaryPackage) {
//		return "mailsucces";
//	}
//
//	@PostMapping("/updateCandidate/{candidateId}/{addressId}")
//	public String updateCondidate(@Valid Candidate candidate, @Valid Address address,
//			@PathVariable("candidateId") int id, @PathVariable("addressId") int addressId, BindingResult result,
//			RedirectAttributes redirect) throws IOException {
//
//		if (result.hasErrors()) {
//
//			return "updateCandidate";
//		}
//		// de MULTIPARTFILE WORDT HIER NOG NIET OPGESLAGEN
//
//		if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
//			MultipartFile file = candidate.getFile();
//			Path filenameAndPath = Paths.get(UPLOADDIRECTORY, file.getOriginalFilename());
//			Files.write(filenameAndPath, file.getBytes());
//			// set link to cv
//			candidate.setCvLink(file.getOriginalFilename());
//		}
//
//		candidateservice.saveOrUpdateCandidate(id, candidate, address, addressId);
//		redirect.addFlashAttribute("success", "Saved employee successfully!");
//		return "updatesucces";
//
//	}
//




//	@GetMapping("/searchcv2/{candidateId}")
//	public String searchCv2(Model model, @PathVariable("candidateId") int id, HttpServletResponse response) throws IOException {
//
//		//String returnValue = "toupdatecv";
//		Optional<NewCandidateFE> tmp = newCandidateFERepository.findById(id);
//
//
//    System.out.println(tmp.get().getCvLink());
//
//		// de kandidaat bestaat
////		if (tmp.isPresent()) {
////			if (!StringUtils.isEmpty(tmp.get().getCvLink())) {
////				newCandidateServiceFE.downloadCv(tmp.get().getCvLink(), response);
////			}
////		}
//
//		model.addAttribute("candidate", tmp.get());
//    return "candidate/add";
//	}

//  @GetMapping("/files/{filename:.+}")
//  @ResponseBody
//  public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//    Resource file = storageService.loadAsResource(filename);
//    return ResponseEntity.ok()
//        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//        .body(file);
//  }




  // ADDED FOR TEMP REASONS
	@GetMapping("/add-candidate")
	public String addOrUpdateCandidateView(Locale locale, Model model) {
		model.addAttribute("candidate", new NewCandidateFE());
		return "candidate/add";
	}

	@PostMapping("/add-candidate")
  public String addCandidate(Locale locale, Model model, @Valid @ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, BindingResult result, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
	  if (result.hasErrors()) {
      return returnWithErrors(model, newCandidateFE, messageSource.getMessage("form.error.submission", null, locale), "candidate/add");
    }

    try {
      String countryCode = countryService.getCountryCodeByName(newCandidateFE.getAddress().getCountrycode());
      newCandidateFE.getAddress().setCountrycode(countryCode);
    } catch (final CountryNotFoundException e) {
      return returnWithErrors(model, newCandidateFE, e.getMessage(), "candidate/add");
    }

    if (newCandidateFE.getFile() != null && !newCandidateFE.getFile().isEmpty()) {
      try {
        fileStorageService.checkFileExtentsion(newCandidateFE.getFile());
        newCandidateFE.setCvLink(fileStorageService.storeCV(newCandidateFE.getFile()));
      } catch (final FileStorageException e) {
        return returnWithErrors(model, newCandidateFE, e.getMessage(), "candidate/add");
      }
    }

    setTechnologies(newCandidateFE);
    newCandidateServiceFE.addCandidate(newCandidateFE);
    sessionStatus.setComplete();
    redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.added", new Object[]{newCandidateFE.getName()}, locale));
    return "redirect:/add-candidate"; // Todo: redirect to overview candidates
  }

  @GetMapping("/edit-candidate/{candidateId}")
  public String editCandidateView(Locale locale, Model model, @PathVariable("candidateId") NewCandidateFE newCandidateFE, RedirectAttributes redirectAttributes) {
    if (newCandidateFE == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("candidate.doesNotExist", null, locale));
      return "redirect:/add-candidate"; // Todo: redirect to overview candidates
    }

    if (newCandidateFE.getAddress().getCountrycode() != null) {
      try {
        String country = countryService.getCountryByCode(newCandidateFE.getAddress().getCountrycode());
        newCandidateFE.getAddress().setCountrycode(country);
      } catch (final CountryNotFoundException e) {
        return returnWithErrors(model, newCandidateFE, e.getMessage(), "candidate/edit");
      }
    }

    model.addAttribute(CANDIDATE, newCandidateFE);
    return "candidate/edit";
  }

  @PostMapping("/edit-candidate/{candidateId}")
  @ExceptionHandler(MultipartException.class)
  public String saveCandidate(Locale locale, Model model, @Validated @ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, BindingResult result, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
    if (result.hasErrors()) {
      return returnWithErrors(model, newCandidateFE, messageSource.getMessage("form.error.submission", null, locale), "candidate/edit");
    }

    try {
      String countryCode = countryService.getCountryCodeByName(newCandidateFE.getAddress().getCountrycode());
      newCandidateFE.getAddress().setCountrycode(countryCode);
    } catch (final CountryNotFoundException e) {
      return returnWithErrors(model, newCandidateFE, e.getMessage(), "candidate/edit");
    }

    if (newCandidateFE.getFile() != null && !newCandidateFE.getFile().isEmpty()) {
      NewCandidateFE old = newCandidateServiceFE.getCandidateById(newCandidateFE.getId());

      try {
        fileStorageService.checkFileExtentsion(newCandidateFE.getFile());
        newCandidateFE.setCvLink(fileStorageService.storeCV(newCandidateFE.getFile()));
        if (old.getCvLink() != null && !old.getCvLink().isEmpty()) {
          fileStorageService.deleteCv(old.getCvLink());
        }
      } catch (final FileStorageException | MyFileNotFoundException e) {
        return returnWithErrors(model, newCandidateFE, e.getMessage(), "candidate/edit");
      }
    }

    setTechnologies(newCandidateFE);

    try {
      newCandidateServiceFE.saveCandidate(newCandidateFE);
      sessionStatus.setComplete();
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{newCandidateFE.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    return "redirect:/add-candidate"; // Todo: redirect to overview candidates
  }

  @GetMapping("/delete-candidate/{candidateId}")
  public String deleteCandidate(Locale locale, @PathVariable("candidateId") NewCandidateFE newCandidateFE, RedirectAttributes redirectAttributes) {
    if (newCandidateFE == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("candidate.doesNotExist", null, locale));
      return "redirect:/add-candidate"; // Todo: redirect to overview candidates
    }

    try {
      newCandidateServiceFE.deleteCandidate(newCandidateFE);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.deleted", new Object[]{newCandidateFE.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

	  return "redirect:/add-candidate"; // Todo: redirect to overview candidates
  }

  @GetMapping("/downloadCv/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, @PathVariable String fileName, HttpServletRequest request) {
    Resource resource = fileStorageService.loadCvAsResource(fileName);
    String contentType = null;

    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      System.out.println("Could not determine file type.");
    }

    if(contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "cv_" + newCandidateFE.getName() + "-" + newCandidateFE.getLastName() + "." + FilenameUtils.getExtension(resource.getFilename()) + "\"").body(resource);
  }

  @GetMapping("/deleteCv/{candidateId}")
  public String deleteFile(Locale locale, @ModelAttribute("candidateId") NewCandidateFE newCandidateFE, RedirectAttributes redirectAttributes) {

	  // Todo: add delete function of cv/file

    try {
      if (newCandidateFE.getCvLink() != null && !newCandidateFE.getCvLink().isEmpty()) {
        fileStorageService.deleteCv(newCandidateFE.getCvLink());
      }
      newCandidateFE.setCvLink("");
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{newCandidateFE.getName()}, locale));
    } catch (final FileStorageException | MyFileNotFoundException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    try {
      newCandidateServiceFE.saveCandidate(newCandidateFE);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{newCandidateFE.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

	  return "redirect:/edit-candidate/" + newCandidateFE.getId();
  }

  // Autocomplete calls.
  @GetMapping(value = "/countries", produces = "application/json")
  @ResponseBody
  public List<Country> autocompleteCountries(@RequestParam("str") String search) {
    List<Country> suggestions = new ArrayList<>();

    for (Country country : countryService.getAllCountries()) {
      if (country.getName().toLowerCase().contains(search.toLowerCase())) {
        suggestions.add(country);
      }
    }

    return suggestions;
  }

  @GetMapping(value = "/technologies", produces = "application/json")
  @ResponseBody
  public List<Technology> autocompleteTechnologies(@RequestParam("str") String search) {
    List<Technology> suggestions = new ArrayList<>();

	  for (Technology technology : technologyService.getAllTechnologies()) {
	    if (technology.getName().toLowerCase().contains(search.toLowerCase())) {
	      suggestions.add(technology);
      }
    }

    return suggestions;
  }

  // Ajax calls.
  @PostMapping(path = "/addSalaryPackage")
  public String addProposalPackage(@ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, HttpServletRequest request) {
    newCandidateFE.addProposalSalaryPackage(new SalaryPackage());

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #proposalList";
    } else {
      return "candidate/edit";
    }
  }

  @PostMapping(path = "/removeSalaryPackage", params = "removeItem")
  public String removeProposalPackage(@ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, @RequestParam("removeItem") int index, HttpServletRequest request) {
    newCandidateFE.removeProposalSalaryPackage(index);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #proposalList";
    } else {
      return "candidate/edit";
    }
  }

//  @PostMapping(path = "/resetCurrentSalary")
//  public String resetCurrentSalary(@ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, HttpServletRequest request) {
//    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
//      return "fragments/form :: #currentSalary";
//    } else {
//      return "candidate/edit";
//    }
//  }

  @PostMapping(path = "/addSkillTechnology")
  public String addSkillTechnology(@ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, HttpServletRequest request) {
    newCandidateFE.getSkill().addSkillTechnology(new SkillTechnology());

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #skillTechnologies";
    } else {
      return "candidate/edit";
    }
  }

  @PostMapping(path = "/removeSkillTechnology", params = "removeItem")
  public String removeSkillTechnology(@ModelAttribute(CANDIDATE) NewCandidateFE newCandidateFE, @RequestParam("removeItem") int index, HttpServletRequest request) {
    newCandidateFE.getSkill().removeSkillTechnology(index);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #skillTechnologies";
    } else {
      return "candidate/edit";
    }
  }

  // Custom methods.
  private String returnWithErrors(Model model, NewCandidateFE newCandidateFE, String errorMessage, String returnUrl) {
    model.addAttribute("candidate", newCandidateFE);
    model.addAttribute("countries", countryService.getAllCountries());
    model.addAttribute("errorMessage", errorMessage);
    return returnUrl;
  }

  private void setTechnologies(@ModelAttribute(CANDIDATE) @Validated NewCandidateFE newCandidateFE) {
    if (newCandidateFE.getSkill().getTechnologies() != null && !newCandidateFE.getSkill().getTechnologies().isEmpty()) {
      for (SkillTechnology skillTechnology : newCandidateFE.getSkill().getTechnologies()) {
        Technology technology = technologyService.findByName(skillTechnology.getTechnology().getName());
        if (technology != null) {
          skillTechnology.setTechnology(technology);
        }
      }
    }
  }

}