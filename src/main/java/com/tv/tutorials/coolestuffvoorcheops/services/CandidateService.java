package com.tv.tutorials.coolestuffvoorcheops.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.model.Address;
import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.AddressRepository;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.CandidateRepository;

@Service
public class CandidateService implements ICandidateService {

	public String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private SkillsService skillsService;
	@Autowired
	ApplicationProcessService applicationProcessService;
	
	@Override
	public List<Candidate> getAllCandidates() {
		List<Candidate> list = new ArrayList<>();
		candidateRepository.findAll().forEach(e -> list.add(e));
		return list;
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
		candidateRepository.save(candidate)	;
	}

	@Override
	public void deleteCandidate(int candidateId) {
		candidateRepository.delete(getCandidateById(candidateId));
	}

	public List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName) {
		List<Candidate> list =  candidateRepository.findAllByNameLikeOrSirNameLike(name, sirName) ;			
		return list;
	}
		
	public List <Candidate> findAllDotnet(){
		List<Integer> skillsIds =skillsService.findAllDotnet();
		Iterable<Candidate>   candidates= candidateRepository.findAllBySkillsIdIn(skillsIds);
		return (List<Candidate>) candidates;	
	}
	
	public List <Candidate> findAllJava(){
		List<Integer> skillsIds =skillsService.findAllJava();
		Iterable<Candidate>   candidates= candidateRepository.findAllBySkillsIdIn(skillsIds);
		return (List<Candidate>) candidates;		
	}
	
	public List <Candidate> findAllFrontend(){
		List<Integer> skillsIds =skillsService.findAllFrontend();
		Iterable<Candidate>   candidates= candidateRepository.findAllBySkillsIdIn(skillsIds);
		return (List<Candidate>) candidates;		
	}
	
	public void saveOrUpdateCandidate(int id, @Valid Candidate candidate, @Valid Address address,  @Valid int addressId) {
		Optional<Candidate> tmp = candidateRepository.findById(id);
		if (tmp.isPresent() ) {
			Candidate s =tmp.get();
			
			s= candidate;
			s.setId(id);
			
			// update address
			System.out.println("addressidttt = "+addressId);
			Optional<Address> tmpAddress = addressRepository.findById(addressId);
			if (tmpAddress.isPresent() ) {
				Address s2 =tmpAddress.get();
				s2= address;
				s2.setId(addressId);
				addressRepository.save(s2);
			}
			//
			
			candidateRepository.save(s);
			System.out.println("het updaten is gelukt");
		}
		else {
			candidateRepository.save(candidate);
		}
		
	}
	
	public void downloadCv(String cvLink , HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out;
	
		out = response.getWriter();
			
		String gurufile = cvLink;
		String gurupath = uploadDirectory;
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ gurufile + "\"");
 
		FileInputStream fileInputStream = new FileInputStream(gurupath
				+"\\"+ gurufile);
 
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
	}

	public List<Candidate> findAllRecruited() {
		List<Integer> applicationProcessIds =   applicationProcessService.findAllRecruited();
		//Iterable<Candidate>   candidates= candidateRepository.findAllById(candidateIds);
		Iterable<Candidate> candidates= candidateRepository.findAllByApplicationProcessIdIn(applicationProcessIds);
		return (List<Candidate>) candidates;
	}
	
	public List<Candidate>findAllRecruitedIn(List<Integer>applicationProcessId){
		System.out.println("lengte "+applicationProcessId.size());
		
		List<Candidate> candidates = new ArrayList<Candidate>() ;
		
		List<ApplicationProcess> t = applicationProcessService.getAllApplicationProcessById(applicationProcessId);
		System.out.println("lengte "+ t.size());
		if (t != null) {
			List<ApplicationProcess> applicationProcessIdsRecruited = applicationProcessService.findAllByIsRecruitedIn(t);
			
			for (ApplicationProcess applicationProcess : applicationProcessIdsRecruited) {
				candidates.add( candidateRepository.findByapplicationProcessId(applicationProcess.getId())      );
			}
			
		}
		
		if(candidates != null){
		}

		return  candidates;
	}
	
	public List<Candidate> getAllCandidatesWithActiveApplicationProcess() {
		List<Integer> applicationProcessIds = applicationProcessService.getAllCandidatesWithActiveApplicationProcess();
		Iterable<Candidate> candidates= candidateRepository.findAllByApplicationProcessIdIn(applicationProcessIds);
		return (List<Candidate>) candidates;
	}

	public List<Candidate> getAllCandidatesWithoutActiveApplicationProcess() {
		List<Integer> applicationProcessIds = applicationProcessService.getAllCandidatesWithoutActiveApplicationProcess();
		Iterable<Candidate> candidates= candidateRepository.findAllByApplicationProcessIdIn(applicationProcessIds);
		return (List<Candidate>) candidates;
	}

	public List<Candidate> findByExperienceGreaterThan(int experience, List<Candidate> candidates) {
		
		List<Integer> skillsIdList = candidates.stream().map(Candidate::getSkillsId).collect(Collectors.toList());
				
		//List<Integer> skillId=  candidateRepository.findAllBySkillsIdIn(skillsIdList)
		List<Integer> filteredSkillsId =skillsService.findAllByExperienceGreaterThan(experience, skillsIdList);
		// 1 skillid komt terug
		candidates =(List<Candidate>) candidateRepository.findAllBySkillsIdIn(filteredSkillsId);
		//candidates.addAll((Collection<? extends Candidate>) candidateRepository.findAllBySkillsIdIn(filteredSkillsId));
		System.out.println("lengtelijsty"+candidates.size());
		
		return candidates;
	}

}
