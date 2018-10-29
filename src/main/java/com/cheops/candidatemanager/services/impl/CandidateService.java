package com.cheops.candidatemanager.services.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.cheops.candidatemanager.repositories.AddressRepository;
import com.cheops.candidatemanager.repositories.CandidateRepository;
import com.cheops.candidatemanager.services.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.models.Address;
import com.cheops.candidatemanager.models.ApplicationProcess;
import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;
import com.cheops.candidatemanager.models.Skills;

@Service
public class CandidateService implements ICandidateService {

	public String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private SkillsService skillsService;
	@Autowired
	ApplicationProcessService applicationProcessService;

	@Override
	public List<CandidateSearchResolver> findAllByNameLikeOrSirNameLike(String name, String sirName) {

		List<Candidate> candidates = candidateRepository.findAllByNameLikeOrSirNameLike(name, sirName);

		return fillExpertiseAndStatus(candidates);
	}

	private List<CandidateSearchResolver> fillExpertiseAndStatus(List<Candidate> candidates) {
		List<CandidateSearchResolver> candidateResolverList = new ArrayList<CandidateSearchResolver>();
		for (Candidate candidate : candidates) {
			// Everything related to the skill
			StringBuffer expertise = new StringBuffer("");
			if (candidate.getSkillsId() != 0) {
				Skills skill = skillsService.getSkillsById(candidate.getSkillsId());

				if (skill.isDotnet()) {
					expertise.append(".Net");
					if (skill.isJava() || skill.isFrontend()) {
						expertise.append(", ");
						if (skill.isJava()) {
							expertise.append("Java");
						}
						if (skill.isFrontend()) {
							expertise.append(", ");
							expertise.append("Front-End");
						}

					}
				} else if (skill.isJava()) {
					expertise.append("Java");
					if (skill.isFrontend()) {
						expertise.append(", ");
						expertise.append("Front-End");
					}
				} else if (skill.isFrontend()) {
					expertise.append("Front-End");
				}
			}

			// ApplicationProcess Logic
			String applicationStatus = "";
			if (candidate.getApplicationProcessId() != 0 ) {
				ApplicationProcess applicationProcess = applicationProcessService
						.getApplicationProcessById(candidate.getApplicationProcessId());
				if (applicationProcess.getToBeInvitedForFirstConversation()) {
					applicationStatus = "Eerste interview";
				} else if (applicationProcess.getToBeInvitedForTechnicalConversation()) {
					applicationStatus = "Technisch interview";
				} else if (applicationProcess.getIsRecruited()) {
					applicationStatus = "In dienst";
				} else {
					applicationStatus = "(Nog) niet uitgenodigd";
				}
			}

			CandidateSearchResolver candidateSearchResolver = new CandidateSearchResolver(candidate,
					expertise.toString(), applicationStatus);
			candidateResolverList.add(candidateSearchResolver);
		}
		return candidateResolverList;
	}

	@Override
	public List<CandidateSearchResolver> getAllCandidates() {

		List<Candidate> candidates = new ArrayList<>();
		candidateRepository.findAll().forEach(e -> candidates.add(e));

		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public Candidate getCandidateById(int candidateId) {
		Candidate e = candidateRepository.findById(candidateId).get();
		return e;
	}

	@Override
	public Candidate addCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
		return candidate;
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
	}

	@Override
	public void deleteCandidate(int candidateId) {
		candidateRepository.delete(getCandidateById(candidateId));
	}

	@Override
	public List<CandidateSearchResolver> findAllDotnet() {

		List<Integer> skillsIds = skillsService.findAllDotnet();
		List<Candidate> candidates = candidateRepository.findAllBySkillsIdIn(skillsIds);

		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<CandidateSearchResolver> findAllJava() {
		List<Integer> skillsIds = skillsService.findAllJava();
		List<Candidate> candidates = candidateRepository.findAllBySkillsIdIn(skillsIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<CandidateSearchResolver> findAllFrontend() {
		List<Integer> skillsIds = skillsService.findAllFrontend();
		List<Candidate> candidates = candidateRepository.findAllBySkillsIdIn(skillsIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public void saveOrUpdateCandidate(int id, @Valid Candidate candidate, @Valid Address address,
			@Valid int addressId) {
		Optional<Candidate> tmp = candidateRepository.findById(id);
		if (tmp.isPresent()) {
			Candidate s = tmp.get();

			s = candidate;
			s.setId(id);

			// update address
			Optional<Address> tmpAddress = addressRepository.findById(addressId);
			if (tmpAddress.isPresent()) {
				Address s2 = tmpAddress.get();
				s2 = address;
				s2.setId(addressId);
				addressRepository.save(s2);
			}
			//

			candidateRepository.save(s);
		} else {
			candidateRepository.save(candidate);
		}

	}

	@Override
	public void downloadCv(String cvLink, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out;

		out = response.getWriter();

		String gurufile = cvLink;
		String gurupath = uploadDirectory;
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + gurufile + "\"");

		FileInputStream fileInputStream = new FileInputStream(gurupath + "\\" + gurufile);

		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}

	@Override
	public List<CandidateSearchResolver> findAllRecruited() {
		List<Integer> applicationProcessIds = applicationProcessService.findAllRecruited();
		List<Candidate> candidates = candidateRepository.findAllByApplicationProcessIdIn(applicationProcessIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<CandidateSearchResolver> findAllRecruitedIn(List<Integer> applicationProcessId) {

		List<Candidate> candidates = new ArrayList<Candidate>();

		List<ApplicationProcess> t = applicationProcessService.getAllApplicationProcessById(applicationProcessId);
		if (t != null) {
			List<ApplicationProcess> applicationProcessIdsRecruited = applicationProcessService
					.findAllByIsRecruitedIn(t);

			for (ApplicationProcess applicationProcess : applicationProcessIdsRecruited) {
				Candidate candidate = candidateRepository.findByapplicationProcessId(applicationProcess.getId());
				if (candidate != null) {
					candidates.add(candidate);
				}
			}

		}
		return fillExpertiseAndStatus(candidates);

	}

	@Override
	public List<CandidateSearchResolver> getAllCandidatesWithActiveApplicationProcess() {
		List<Integer> applicationProcessIds = applicationProcessService.getAllCandidatesWithActiveApplicationProcess();
		List<Candidate> candidates = candidateRepository.findAllByApplicationProcessIdIn(applicationProcessIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<Candidate> getAllCandidatesWithoutActiveApplicationProcess() {
		List<Integer> applicationProcessIds = applicationProcessService
				.getAllCandidatesWithoutActiveApplicationProcess();
		Iterable<Candidate> candidates = candidateRepository.findAllByApplicationProcessIdIn(applicationProcessIds);
		return (List<Candidate>) candidates;
	}

	@Override
	public List<CandidateSearchResolver> findByExperienceGreaterThan(int experience,
			List<CandidateSearchResolver> candidates) {

		List<Integer> skillsIdList = candidates.stream().map(c -> c.getCandidate().getSkillsId())
				.collect(Collectors.toList());

		List<Integer> filteredSkillsId = skillsService.findAllByExperienceGreaterThan(experience, skillsIdList);

		List<Candidate> candidatesList = candidateRepository.findAllBySkillsIdIn(filteredSkillsId);
		return fillExpertiseAndStatus(candidatesList);
	}

}
