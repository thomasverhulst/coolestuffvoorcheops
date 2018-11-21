package com.cheops.candidatemanager.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.cheops.candidatemanager.services.IMeetingService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.enums.ConversationType;
import com.cheops.candidatemanager.models.Meeting;
import com.cheops.candidatemanager.repositories.MeetingRepository;

@Service
public class MeetingService implements IMeetingService {

	@Autowired
	MeetingRepository meetingRepository;

	@Override
	public List<Meeting> getUpcomingMonthsFirstScreenings() {
		Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		Date month = DateUtils.addMonths(new Date(), 1);
    return meetingRepository.findAllBymeetingDateBetweenAndConversationType(today, month,ConversationType.FIRST);
	}

	@Override
	public List<Meeting> getUpcomingMonthsTechnicalScreenings() {
		Date today = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
		Date month = DateUtils.addMonths(new Date(), 1);
    return meetingRepository.findAllBymeetingDateBetweenAndConversationType(today, month,ConversationType.TECHNICAL);
	}

}
