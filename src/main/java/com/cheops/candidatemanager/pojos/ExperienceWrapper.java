package com.cheops.candidatemanager.pojos;

import java.util.Arrays;
import java.util.List;

public class ExperienceWrapper {

	private List<String> experienceIntervals = Arrays.asList("<= 2", "3 - 5", "> 5");
	
	//List<String> strings 

	public List<String> getExperienceIntervals() {
		return experienceIntervals;
	}

	public void setExperienceIntervals(List<String> experienceIntervals) {
		this.experienceIntervals = experienceIntervals;
	}
	
}


