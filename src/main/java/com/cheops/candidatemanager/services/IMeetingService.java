package com.cheops.candidatemanager.services;

import com.cheops.candidatemanager.models.Meeting;

import java.util.List;

public interface IMeetingService {

  List<Meeting> getUpcomingMonthsFirstScreenings();

  List<Meeting> getUpcomingMonthsTechnicalScreenings();

}
