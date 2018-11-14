package com.cheops.candidatemanager.pojos;

import java.util.Arrays;
import java.util.List;

public class CitieListWrapper {
	private List<String> cities=Arrays.asList("London", "Tokyo", "New York","'t Stad");
	
	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	
}
