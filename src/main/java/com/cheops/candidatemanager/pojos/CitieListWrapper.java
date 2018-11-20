package com.cheops.candidatemanager.pojos;

import java.util.Arrays;
import java.util.List;

public class CitieListWrapper {
	private List<String> cities=Arrays.asList("'t Stad", "P1", "P2","p3");
	
	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	
}
