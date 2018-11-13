package com.cheops.candidatemanager.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.models.ConversationType;
import com.cheops.candidatemanager.models.Meeting;
import com.cheops.candidatemanager.repositories.MeetingRepository;

@Service

public class MeetingService {

	@Autowired
	MeetingRepository meetingRepository;
	public List<Meeting> getUpcomingMonthsFirstScreenings() {
		Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		Date month;
		month = DateUtils.addMonths(new Date(), 1);

		return meetingRepository.findAllBymeetingDateBetweenAndConversationType(today, month,ConversationType.FIRST);
	}

	public List<Meeting> getUpcomingMonthsTechnicalScreenings() {
		Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		Date month;
		month = DateUtils.addMonths(new Date(), 1);
	
		return meetingRepository.findAllBymeetingDateBetweenAndConversationType(today, month,ConversationType.SECOND);
	}

}
