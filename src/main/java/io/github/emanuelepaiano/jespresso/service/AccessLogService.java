package io.github.emanuelepaiano.jespresso.service;

import java.util.List;

import io.github.emanuelepaiano.jespresso.entity.AccessLog;
import io.github.emanuelepaiano.jespresso.exception.AlreadyExistsException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;

/**
 * The Interface AccessLogService.
 */
public interface AccessLogService {
	
	/**
	 * Adds the access log.
	 *
	 * @param accessLog the access log
	 * @return the access log
	 * @throws AlreadyExistsException the already exists exception
	 */
	public AccessLog addAccessLog(AccessLog accessLog);
	
	/**
	 * Update access log.
	 *
	 * @param id the id
	 * @param accessLog the access log
	 * @return the user
	 * @throws NoContentException the not found exception
	 */
	public AccessLog updateAccessLog(Long id, AccessLog accessLog) throws NoContentException;
	
	/**
	 * Delete access log.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws NoContentException the not found exception
	 */
	public boolean deleteAccessLog(Long id) throws NoContentException;
	
	/**
	 * Gets the access log.
	 *
	 * @param id the id
	 * @return the access log
	 * @throws NoContentException the not found exception
	 */
	public AccessLog getAccessLog(Long id) throws NoContentException;
	
	/**
	 * Gets the access logs.
	 *
	 * @return the access logs
	 * @throws NoContentException the not found exception
	 */
	public List<AccessLog> getAccessLogs() throws NoContentException;
}
