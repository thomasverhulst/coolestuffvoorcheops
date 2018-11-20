package com.cheops.candidatemanager.controllers;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cheops.candidatemanager.exceptions.*;
import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.services.ICandidateService;
import com.cheops.candidatemanager.services.ICountryService;
import com.cheops.candidatemanager.services.IFileStorageService;
import com.cheops.candidatemanager.services.ITechnologyService;
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
  private IFileStorageService fileStorageService;

  @Autowired
  private ICandidateService newCandidateServiceFE;

  @Autowired
  private ICountryService countryService;

  @Autowired
  private ITechnologyService technologyService;

	@GetMapping("/add-candidate")
	public String addOrUpdateCandidateView(Locale locale, Model model) {
		model.addAttribute("candidate", new Candidate());
		return "candidate/add";
	}

	@PostMapping("/add-candidate")
  public String addCandidate(Locale locale, Model model, @Valid @ModelAttribute(CANDIDATE) Candidate candidate, BindingResult result, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
	  if (result.hasErrors()) {
      return returnWithErrors(model, candidate, messageSource.getMessage("form.error.submission", null, locale), "candidate/add");
    }

    try {
      String countryCode = countryService.getCountryCodeByName(candidate.getAddress().getCountrycode());
      candidate.getAddress().setCountrycode(countryCode);
    } catch (final CountryNotFoundException e) {
      return returnWithErrors(model, candidate, e.getMessage(), "candidate/add");
    }

    if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
      try {
        fileStorageService.checkFileExtentsion(candidate.getFile());
        candidate.setCvLink(fileStorageService.storeCV(candidate.getFile()));
      } catch (final FileStorageException e) {
        return returnWithErrors(model, candidate, e.getMessage(), "candidate/add");
      }
    }

    setTechnologies(candidate);
    newCandidateServiceFE.addCandidate(candidate);
    sessionStatus.setComplete();
    redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.added", new Object[]{candidate.getName()}, locale));
    return "redirect:/add-candidate"; // Todo: redirect to overview candidates
  }

  @GetMapping("/edit-candidate/{candidateId}")
  public String editCandidateView(Locale locale, Model model, @PathVariable("candidateId") Candidate candidate, RedirectAttributes redirectAttributes) {
    if (candidate == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("candidate.doesNotExist", null, locale));
      return "redirect:/add-candidate"; // Todo: redirect to overview candidates
    }

    if (candidate.getAddress().getCountrycode() != null) {
      try {
        String country = countryService.getCountryByCode(candidate.getAddress().getCountrycode());
        candidate.getAddress().setCountrycode(country);
      } catch (final CountryNotFoundException e) {
        return returnWithErrors(model, candidate, e.getMessage(), "candidate/edit");
      }
    }

    model.addAttribute(CANDIDATE, candidate);
    return "candidate/edit";
  }

  @PostMapping("/edit-candidate/{candidateId}")
  @ExceptionHandler(MultipartException.class)
  public String saveCandidate(Locale locale, Model model, @Validated @ModelAttribute(CANDIDATE) Candidate candidate, BindingResult result, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
    if (result.hasErrors()) {
      return returnWithErrors(model, candidate, messageSource.getMessage("form.error.submission", null, locale), "candidate/edit");
    }

    try {
      String countryCode = countryService.getCountryCodeByName(candidate.getAddress().getCountrycode());
      candidate.getAddress().setCountrycode(countryCode);
    } catch (final CountryNotFoundException e) {
      return returnWithErrors(model, candidate, e.getMessage(), "candidate/edit");
    }

    if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
      Candidate old = newCandidateServiceFE.getCandidateById(candidate.getId());

      try {
        fileStorageService.checkFileExtentsion(candidate.getFile());
        candidate.setCvLink(fileStorageService.storeCV(candidate.getFile()));
        if (old.getCvLink() != null && !old.getCvLink().isEmpty()) {
          fileStorageService.deleteCv(old.getCvLink());
        }
      } catch (final FileStorageException | MyFileNotFoundException e) {
        return returnWithErrors(model, candidate, e.getMessage(), "candidate/edit");
      }
    }

    setTechnologies(candidate);

    try {
      newCandidateServiceFE.saveCandidate(candidate);
      sessionStatus.setComplete();
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{candidate.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    return "redirect:/add-candidate"; // Todo: redirect to overview candidates
  }

  @GetMapping("/delete-candidate/{candidateId}")
  public String deleteCandidate(Locale locale, @PathVariable("candidateId") Candidate candidate, RedirectAttributes redirectAttributes) {
    if (candidate == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("candidate.doesNotExist", null, locale));
      return "redirect:/add-candidate"; // Todo: redirect to overview candidates
    }

    try {
      newCandidateServiceFE.deleteCandidate(candidate);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.deleted", new Object[]{candidate.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

	  return "redirect:/add-candidate"; // Todo: redirect to overview candidates
  }

  @GetMapping("/downloadCv/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@ModelAttribute(CANDIDATE) Candidate candidate, @PathVariable String fileName, HttpServletRequest request) {
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

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "cv_" + candidate.getName() + "-" + candidate.getLastName() + "." + FilenameUtils.getExtension(resource.getFilename()) + "\"").body(resource);
  }

  @GetMapping("/deleteCv/{candidateId}")
  public String deleteFile(Locale locale, @ModelAttribute("candidateId") Candidate candidate, RedirectAttributes redirectAttributes) {

	  // Todo: add delete function of cv/file

    try {
      if (candidate.getCvLink() != null && !candidate.getCvLink().isEmpty()) {
        fileStorageService.deleteCv(candidate.getCvLink());
      }
      candidate.setCvLink("");
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{candidate.getName()}, locale));
    } catch (final FileStorageException | MyFileNotFoundException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    try {
      newCandidateServiceFE.saveCandidate(candidate);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{candidate.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

	  return "redirect:/edit-candidate/" + candidate.getId();
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
  public String addProposalPackage(@ModelAttribute(CANDIDATE) Candidate candidate, HttpServletRequest request) {
    candidate.addProposalSalaryPackage(new SalaryPackage());

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #proposalList";
    } else {
      return "candidate/edit";
    }
  }

  @PostMapping(path = "/removeSalaryPackage", params = "removeItem")
  public String removeProposalPackage(@ModelAttribute(CANDIDATE) Candidate candidate, @RequestParam("removeItem") int index, HttpServletRequest request) {
    candidate.removeProposalSalaryPackage(index);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #proposalList";
    } else {
      return "candidate/edit";
    }
  }

//  @PostMapping(path = "/resetCurrentSalary")
//  public String resetCurrentSalary(@ModelAttribute(CANDIDATE) Candidate newCandidateFE, HttpServletRequest request) {
//    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
//      return "fragments/form :: #currentSalary";
//    } else {
//      return "candidate/edit";
//    }
//  }

  @PostMapping(path = "/addSkillTechnology")
  public String addSkillTechnology(@ModelAttribute(CANDIDATE) Candidate candidate, HttpServletRequest request) {
    candidate.getSkill().addSkillTechnology(new SkillTechnology());

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #skillTechnologies";
    } else {
      return "candidate/edit";
    }
  }

  @PostMapping(path = "/removeSkillTechnology", params = "removeItem")
  public String removeSkillTechnology(@ModelAttribute(CANDIDATE) Candidate candidate, @RequestParam("removeItem") int index, HttpServletRequest request) {
    candidate.getSkill().removeSkillTechnology(index);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #skillTechnologies";
    } else {
      return "candidate/edit";
    }
  }

  // Custom methods.
  private String returnWithErrors(Model model, Candidate candidate, String errorMessage, String returnUrl) {
    model.addAttribute("candidate", candidate);
    model.addAttribute("countries", countryService.getAllCountries());
    model.addAttribute("errorMessage", errorMessage);
    return returnUrl;
  }

  private void setTechnologies(@ModelAttribute(CANDIDATE) @Validated Candidate candidate) {
    if (candidate.getSkill().getTechnologies() != null && !candidate.getSkill().getTechnologies().isEmpty()) {
      for (SkillTechnology skillTechnology : candidate.getSkill().getTechnologies()) {
        Technology technology = technologyService.findByName(skillTechnology.getTechnology().getName());
        if (technology != null) {
          skillTechnology.setTechnology(technology);
        }
      }
    }
  }

}