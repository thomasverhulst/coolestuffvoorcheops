package com.cheops.candidatemanager.controllers;

import java.io.IOException;
import java.sql.Timestamp;
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
@RequestMapping("/candidate/")
@SessionAttributes({CandidateController.CANDIDATE})
public class CandidateController {

  static final String CANDIDATE = "candidate";

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private IFileStorageService fileStorageService;

  @Autowired
  private ICandidateService candidateService;

  @Autowired
  private ICountryService countryService;

  @Autowired
  private ITechnologyService technologyService;

	@GetMapping("/add")
	public String addOrUpdateCandidateView(Locale locale, Model model) {
		model.addAttribute("candidate", new Candidate());
		return "candidate/add";
	}

	@PostMapping("/add")
  public String addCandidate(Locale locale, Model model, @Valid @ModelAttribute(CANDIDATE) Candidate candidate, BindingResult result, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
	  if (result.hasErrors()) return returnWithErrors(model, candidate, messageSource.getMessage("form.error.submission", null, locale), "candidate/add");

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

    candidate.setIsAddedTimeStamp(new Timestamp(System.currentTimeMillis()));
    setTechnologies(candidate);
    candidateService.addCandidate(candidate);
    sessionStatus.setComplete();
    redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.added", new Object[]{candidate.getName()}, locale));
    return "redirect:/candidate/add";
  }

  @GetMapping("/edit/{candidateId}")
  public String editCandidateView(Locale locale, Model model, @PathVariable("candidateId") Candidate candidate, RedirectAttributes redirectAttributes) {
    if (candidate == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("candidate.doesNotExist", null, locale));
      return "redirect:/candidate/add"; // Todo: redirect to overview candidates
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

  @PostMapping("/edit/{candidateId}")
  @ExceptionHandler(MultipartException.class)
  public String saveCandidate(Locale locale, Model model, @Validated @ModelAttribute(CANDIDATE) Candidate candidate, BindingResult result, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
    if (result.hasErrors()) return returnWithErrors(model, candidate, messageSource.getMessage("form.error.submission", null, locale), "candidate/edit");

    try {
      String countryCode = countryService.getCountryCodeByName(candidate.getAddress().getCountrycode());
      candidate.getAddress().setCountrycode(countryCode);
    } catch (final CountryNotFoundException e) {
      return returnWithErrors(model, candidate, e.getMessage(), "candidate/edit");
    }

    if (candidate.getFile() != null && !candidate.getFile().isEmpty()) {
      Candidate old = candidateService.getCandidateById(candidate.getId());

      try {
        fileStorageService.checkFileExtentsion(candidate.getFile());
        candidate.setCvLink(fileStorageService.storeCV(candidate.getFile()));
        if (old.getCvLink() != null && !old.getCvLink().isEmpty()) fileStorageService.deleteCv(old.getCvLink());
      } catch (final FileStorageException | MyFileNotFoundException e) {
        return returnWithErrors(model, candidate, e.getMessage(), "candidate/edit");
      }
    }

    setTechnologies(candidate);

    try {
      candidateService.saveCandidate(candidate);
      sessionStatus.setComplete();
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{candidate.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    return "redirect:/candidate/add"; // Todo: redirect to overview candidates
  }

  @GetMapping("/delete/{candidateId}")
  public String deleteCandidate(Locale locale, @PathVariable("candidateId") Candidate candidate, RedirectAttributes redirectAttributes) {
    if (candidate == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("candidate.doesNotExist", null, locale));
      return "redirect:/candidate/add"; // Todo: redirect to overview candidates
    }

    try {
      candidateService.deleteCandidate(candidate);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.deleted", new Object[]{candidate.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

	  return "redirect:/candidate/add"; // Todo: redirect to overview candidates
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
        if (technology != null) skillTechnology.setTechnology(technology);
      }
    }
  }

}