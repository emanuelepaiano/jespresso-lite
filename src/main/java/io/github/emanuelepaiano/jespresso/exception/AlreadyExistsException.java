package io.github.emanuelepaiano.jespresso.exception;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.github.emanuelepaiano.jespresso.dto.response.ErrorResponseDTO;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;

/**
 * The Class AlreadyExistsException.
 */
public class AlreadyExistsException extends HandledException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static Logger logger = Logger.getLogger(AlreadyExistsException.class);
	
	private String itemName = "item";

	/**
	 * Instantiates a new already exists exception.
	 *
	 * @param message the message
	 */
	public AlreadyExistsException(String message, String itemName) {
		super(message);
		this.itemName = itemName;
	}

	/**
	 * Instantiates a new already exists exception.
	 *
	 * @param message the message
	 */
	public AlreadyExistsException(String message) {
		super(message);
	}

	/**
	 * Gets the response entity.
	 *
	 * @return the response entity
	 */
	@Override
	public ResponseEntity<ErrorResponseDTO> getResponseEntity() {
		logger.error(String.format(LoggerConstants.ALREADY_EXISTS_EXCEPTION, "AlreadyExistsException", "getResponseEntity()", this.itemName));
		return new ResponseEntity<ErrorResponseDTO>(
				new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), 
						String.format("%s already exists.", this.itemName)),
		HttpStatus.BAD_REQUEST);
	}

}
