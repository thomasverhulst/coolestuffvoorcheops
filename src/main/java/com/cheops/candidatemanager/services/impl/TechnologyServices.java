package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.models.Technology;
import com.cheops.candidatemanager.repositories.TechnologyRepository;
import com.cheops.candidatemanager.services.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyServices implements ITechnologyService {

  @Autowired
  private TechnologyRepository technologyRepository;

  @Override
  public List<Technology> getAllTechnologies() {
    List<Technology> list = new ArrayList<>();
    technologyRepository.findAll().forEach(list::add);
    return list;
  }

  @Override
  public Technology findByName(String name) {
    return technologyRepository.findDistinctFirstByName(name);
  }

}
