package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.services.ICountryService;
import com.cheops.candidatemanager.services.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({AjaxController.CANDIDATE})
public class AjaxController {

  static final String CANDIDATE = "candidate";

  @Autowired
  private ITechnologyService technologyService;

  @Autowired
  private ICountryService countryService;

  // Meeting.
  @PostMapping(value = "/addMeeting")
  public String addMeeting(Candidate candidate, HttpServletRequest request) {
    candidate.addMeeting(new Meeting());
    request.getSession().setAttribute(CANDIDATE, candidate);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #meetingList";
    } else {
      return "candidate/edit";
    }
  }

  @PostMapping(value = "/removeMeeting", params = "removeItem")
  public String removeMeeting(Candidate candidate, @RequestParam("removeItem") int index, HttpServletRequest request) {
    candidate.removeMeeting(index);
    request.getSession().setAttribute(CANDIDATE, candidate);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #meetingList";
    } else {
      return "candidate/edit";
    }
  }

  // Technology.
  @GetMapping(value = "/technologies", produces = "application/json")
  @ResponseBody
  public List<Technology> autocompleteTechnologies(@RequestParam("str") String search) {
    List<Technology> suggestions = new ArrayList<>();

    for (Technology technology : technologyService.getAllTechnologies()) {
      if (technology.getName().toLowerCase().contains(search.toLowerCase())) suggestions.add(technology);
    }

    return suggestions;
  }

  @PostMapping(path = "/addSkillTechnology")
  public String addSkillTechnology(Candidate candidate, HttpServletRequest request) {
    candidate.getSkill().addSkillTechnology(new SkillTechnology());
    request.getSession().setAttribute(CANDIDATE, candidate);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #skillTechnologies";
    } else {
      return "candidate/edit";
    }
  }

  @PostMapping(path = "/removeSkillTechnology", params = "removeItem")
  public String removeSkillTechnology(Candidate candidate, @RequestParam("removeItem") int index, HttpServletRequest request) {
    candidate.getSkill().removeSkillTechnology(index);
    request.getSession().setAttribute(CANDIDATE, candidate);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #skillTechnologies";
    } else {
      return "candidate/edit";
    }
  }

  // Salary.
  @PostMapping(path = "/addSalaryPackage")
  public String addProposalPackage(Candidate candidate, HttpServletRequest request) {
    candidate.addProposalSalaryPackage(new SalaryPackage());
    request.getSession().setAttribute(CANDIDATE, candidate);

    if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
      return "fragments/form :: #proposalList";
    } else {
      return "candidate/edit";
    }
  }

  @PostMapping(path = "/removeSalaryPackage", params = "removeItem")
  public String removeProposalPackage(Candidate candidate, @RequestParam("removeItem") int index, HttpServletRequest request) {
    candidate.removeProposalSalaryPackage(index);
    request.getSession().setAttribute(CANDIDATE, candidate);

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

  // Country.
  @GetMapping(value = "/countries", produces = "application/json")
  @ResponseBody
  public List<Country> autocompleteCountries(@RequestParam("str") String search) {
    List<Country> suggestions = new ArrayList<>();

    for (Country country : countryService.getAllCountries()) {
      if (country.getName().toLowerCase().contains(search.toLowerCase())) suggestions.add(country);
    }

    return suggestions;
  }

}
