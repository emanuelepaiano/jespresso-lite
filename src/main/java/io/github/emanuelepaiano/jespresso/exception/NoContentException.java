package io.github.emanuelepaiano.jespresso.exception;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.emanuelepaiano.jespresso.dto.response.ErrorResponseDTO;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;

/**
 * The Class NotFoundException.
 */
public class NoContentException extends HandledException {

	/** The logger. */
	private static Logger logger = Logger.getLogger(NoContentException.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new not found exception.
	 *
	 * @param message the message
	 */
	public NoContentException(String message) {
		super(message);
	}

	/**
	 * Gets the response entity.
	 *
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ErrorResponseDTO> getResponseEntity() {
		logger.error(String.format(LoggerConstants.NO_CONTENT_EXCEPTION, "NotFoundException", "getResponseEntity()", "item"));
		return new ResponseEntity<ErrorResponseDTO>(
				new ErrorResponseDTO(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString(), 
						String.format(LoggerConstants.NO_CONTENT_EXCEPTION, "NotFoundException", "getResponseEntity()", "item")),
		HttpStatus.NO_CONTENT);
	}

}
