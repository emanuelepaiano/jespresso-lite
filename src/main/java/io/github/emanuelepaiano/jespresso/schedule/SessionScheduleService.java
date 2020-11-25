package io.github.emanuelepaiano.jespresso.schedule;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.service.SessionService;

/**
 * The Class SessionTask.
 */
@Service
public class SessionScheduleService {
	
	/** The session service. */
	@Autowired
	private SessionService sessionService;
	
	/**
	 * Removes the blocked session.
	 */
	@Scheduled(fixedDelayString = "${jespresso.sessions.cleantable.delay}", initialDelay = 15000)
	@Transactional
	public void removeBlockedSession() {
		this.sessionService.cleanSessionTable();
	}
}
