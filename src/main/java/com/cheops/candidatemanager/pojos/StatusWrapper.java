package com.cheops.candidatemanager.pojos;

import java.util.Arrays;
import java.util.List;

public class StatusWrapper {

	private List<String> statusList=Arrays.asList("(Nog) niet uitgenodigd", "Eerste interview", "Technisch interview", "In dienst");

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	
}
