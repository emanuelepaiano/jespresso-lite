package io.github.emanuelepaiano.jespresso.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.entity.AccessLog;
import io.github.emanuelepaiano.jespresso.exception.AlreadyExistsException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.repository.AccessLogRepository;
import io.github.emanuelepaiano.jespresso.service.AccessLogService;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;

/**
 * The Class AccessLogServiceImpl.
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {

	/** The logger. */
	private static Logger logger = Logger.getLogger(AccessLogServiceImpl.class);

	/** The access log service. */
	@Autowired
	private AccessLogRepository accessLogRepository;

	/**
	 * Adds the access log.
	 *
	 * @param accessLog the access log
	 * @return the access log
	 * @throws AlreadyExistsException the already exists exception
	 */
	@Override
	public AccessLog addAccessLog(AccessLog accessLog) {
		return accessLogRepository.save(accessLog);
	}

	/**
	 * Update access log.
	 *
	 * @param id        the id
	 * @param accessLog the access log
	 * @return the access log
	 * @throws NoContentException the not found exception
	 */
	@Override
	public AccessLog updateAccessLog(Long id, AccessLog accessLog) throws NoContentException {
		accessLog = accessLogRepository.save(getAccessLog(id));
		return accessLog;
	}

	/**
	 * Delete access log.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws NoContentException the not found exception
	 */
	@Override
	public boolean deleteAccessLog(Long id) throws NoContentException {
		accessLogRepository.deleteById(id);
		return !accessLogRepository.existsById(id);
	}

	/**
	 * Gets the access log.
	 *
	 * @param id the id
	 * @return the access log
	 * @throws NoContentException the not found exception
	 */
	@Override
	public AccessLog getAccessLog(Long id) throws NoContentException {
		return accessLogRepository.findById(id).orElseThrow(() -> {
			String message = String.format(LoggerConstants.NOT_FOUND_EXCEPTION, this.getClass().getName(),
					"getAccessLog()", "accessLog", id);
			return new NoContentException(message);
		});  
	}

	/**
	 * Gets the access logs.
	 *
	 * @return the access logs
	 * @throws NoContentException the not found exception
	 */
	@Override
	public List<AccessLog> getAccessLogs() throws NoContentException {
		List<AccessLog> accessLogs = this.accessLogRepository.findAll();
		if (!accessLogs.isEmpty()) {
			return accessLogs;
		} else {
			String message = String.format(LoggerConstants.NOT_FOUND_EXCEPTION, this.getClass().getName(),
					"getAccessLogs", "accessLog");
			logger.error(message);           
			throw new NoContentException(message);
		}
	}

}
