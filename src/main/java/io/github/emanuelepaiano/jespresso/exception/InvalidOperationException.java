package io.github.emanuelepaiano.jespresso.exception;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.emanuelepaiano.jespresso.dto.response.ErrorResponseDTO;

/**
 * The Class AlreadyExistsException.
 */
public class InvalidOperationException extends HandledException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static Logger logger = Logger.getLogger(InvalidOperationException.class);
	

	/**
	 * Instantiates a new already exists exception.
	 *
	 * @param message the message
	 */
	public InvalidOperationException(String message) {
		super(message);
	}


	/**
	 * Gets the response entity.
	 *
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ErrorResponseDTO> getResponseEntity() {
		logger.error(this.getMessage());
		return new ResponseEntity<ErrorResponseDTO>(
				new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), this.getMessage()),
		HttpStatus.BAD_REQUEST);
	}

}
