package io.github.emanuelepaiano.jespresso.handler;

import org.jboss.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import io.github.emanuelepaiano.jespresso.controller.SessionController;
import io.github.emanuelepaiano.jespresso.dto.response.ErrorResponseDTO;
import io.github.emanuelepaiano.jespresso.exception.AlreadyExistsException;
import io.github.emanuelepaiano.jespresso.exception.HandledException;
import io.github.emanuelepaiano.jespresso.exception.InvalidOperationException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;

/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(SessionController.class);

	/**
	 * Handle exception.
	 *
	 * @param exception the exception
	 * @param request the request
	 * @return the response entity
	 * @throws Exception 
	 */
	@ExceptionHandler({ AlreadyExistsException.class, NoContentException.class, InvalidOperationException.class, Exception.class })
	public ResponseEntity<ErrorResponseDTO> handleException(Exception exception, WebRequest request) throws Exception {		
		if (exception instanceof HandledException) {
			logger.error("handleException(): " + exception.getMessage());
			return ((HandledException) exception).getResponseEntity();
        } else {
        	logger.error("handleException(): " + exception.getMessage());
        	throw exception;
        }	
	}
}
