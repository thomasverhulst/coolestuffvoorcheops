package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.exceptions.CandidateDoesNotExistException;
import com.cheops.candidatemanager.exceptions.FileStorageException;
import com.cheops.candidatemanager.exceptions.MyFileNotFoundException;
import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.services.ICandidateService;
import com.cheops.candidatemanager.services.IFileStorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

@Controller
@SessionAttributes({CandidateController.CANDIDATE})
public class FileStorageController {

  static final String CANDIDATE = "candidate";

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private IFileStorageService fileStorageService;

  @Autowired
  private ICandidateService candidateService;

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
      candidateService.saveCandidate(candidate);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("candidate.edited", new Object[]{candidate.getName()}, locale));
    } catch (final CandidateDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    return "redirect:/candidate/edit/" + candidate.getId();
  }

}
