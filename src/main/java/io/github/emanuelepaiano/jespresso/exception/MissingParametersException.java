package io.github.emanuelepaiano.jespresso.exception;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.emanuelepaiano.jespresso.dto.response.ErrorResponseDTO;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;

/**
 * The Class NotFoundException.
 */
public class MissingParametersException extends HandledException {

	/** The logger. */
	private static Logger logger = Logger.getLogger(MissingParametersException.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new not found exception.
	 *
	 * @param message the message
	 */
	public MissingParametersException(String message) {
		super(message);
	}

	/**
	 * Gets the response entity.
	 *
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ErrorResponseDTO> getResponseEntity() {
		logger.error(String.format(LoggerConstants.INVALID_REST_BODY, "MissingParametersException", "getResponseEntity()", "item") + ". " + this.getMessage());
		return new ResponseEntity<ErrorResponseDTO>(
				new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), 
						String.format(LoggerConstants.INVALID_REST_BODY, "MissingParametersException", "getResponseEntity()") + ". " + this.getMessage()),
		HttpStatus.BAD_REQUEST);
	}

}
