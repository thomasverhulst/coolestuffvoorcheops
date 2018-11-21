package com.cheops.candidatemanager.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cheops.candidatemanager.enums.ConversationType;
import com.cheops.candidatemanager.models.Meeting;

public interface MeetingRepository extends CrudRepository<Meeting, Integer> {
	List<Meeting> findAllBymeetingDateBetweenAndConversationType(Date today, Date month, ConversationType first);
}
