package com.cheops.candidatemanager.services;

import com.cheops.candidatemanager.models.Technology;

import java.util.List;

public interface ITechnologyService {

	List<Technology> getAllTechnologies();

	Technology findByName(String name);

}
