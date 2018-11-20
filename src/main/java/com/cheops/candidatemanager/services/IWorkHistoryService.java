package com.cheops.candidatemanager.services;

import com.cheops.candidatemanager.models.WorkHistory;

import java.util.List;

public interface IWorkHistoryService {

  List<WorkHistory> getLastMonthsRecruits();

  List<WorkHistory> getLast5Recruited();

  List<WorkHistory> getAllExEmployees();

}
