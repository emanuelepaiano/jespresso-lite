package io.github.emanuelepaiano.jespresso.exception;

import org.springframework.http.ResponseEntity;

import io.github.emanuelepaiano.jespresso.dto.response.ErrorResponseDTO;

/**
 * The Class HandledException.
 */
public abstract class HandledException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the response body.
	 *
	 * @return the response body
	 */
	public abstract ResponseEntity<ErrorResponseDTO> getResponseEntity();

	/**
	 * Instantiates a new handled exception.
	 *
	 * @param message the message
	 */
	HandledException(String message) {
		super(message);
	}
	
}
