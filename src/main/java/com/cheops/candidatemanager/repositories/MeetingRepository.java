package com.cheops.candidatemanager.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cheops.candidatemanager.models.ConversationType;
import com.cheops.candidatemanager.models.Meeting;


public interface MeetingRepository extends CrudRepository<Meeting, Integer>{

	//List<Meeting> findAllBymeetingDateDateBetween(Date today, Date month);

	//List<Meeting> findAllBymeetingDateBetweenAndConversationTypeStartingWith(Date today, Date month, String string);

	//List<Meeting> findAllBymeetingDateBetweenAndConversationTypeStartingWith(Date today, Date month,
		//	ConversationType2 first);
	//List<Meeting> findAllByConversationTypeStartingWith(ConversationType second);
	//List<Meeting> findAllBymeetingDateBetweenAndConversationTypeStartingWith(Date today, Date month,
		//	ConversationType2 second);

	//List<Meeting> findAllByConversationType(ConversationType second);

	List<Meeting> findAllBymeetingDateBetweenAndConversationType(Date today, Date month, ConversationType first);

}
