package io.github.emanuelepaiano.jespresso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.emanuelepaiano.jespresso.dto.captiveportal.AuthorizeDeviceRequestDTO;
import io.github.emanuelepaiano.jespresso.dto.response.SuccessResponseDTO;
import io.github.emanuelepaiano.jespresso.exception.MissingParametersException;
import io.github.emanuelepaiano.jespresso.service.CaptivePortalService;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;
import io.github.emanuelepaiano.jespresso.util.UserAgentUtils;

/**
 * The Class CaptivePortalController.
 */
@RestController
@RequestMapping("/api")
public class CaptivePortalController {

	/** The logger. */
	private final Logger logger = Logger.getLogger(CaptivePortalController.class);

	@Autowired
	private CaptivePortalService captivePortalService;

	/**
	 * Authorize device.
	 *
	 * @param request the request
	 * @param result  the result
	 * @return the response entity with true if success, false otherwise
	 * @throws Exception
	 */
	@PostMapping("/authorize-guest")
	public ResponseEntity<Object> authorizeDevice(@RequestBody @Valid AuthorizeDeviceRequestDTO request,
			HttpServletRequest httpRequest) throws Exception {
		request.setIpAddress(httpRequest.getHeader("HTTP_X_FORWARDED_FOR") == null ? 
				httpRequest.getRemoteAddr():
				httpRequest.getHeader("HTTP_X_FORWARDED_FOR"));
		logger.info(httpRequest.getHeader("User-Agent"));
		request.setBrowser(UserAgentUtils.getBrowser(httpRequest));
		request.setOperatingSystem(UserAgentUtils.getOperatingSystem(httpRequest));
		logger.debug(String.format(LoggerConstants.CALLED_CONTROLLER, "CaptivePortalController", "authorizeDevice",
				request.toString()));
		if (!request.getAcceptTou()) {
			throw new MissingParametersException("You should accept term of use for surfing");
		}
		
		return new ResponseEntity<Object>(new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(),
				captivePortalService.authorizeDevice(request)), HttpStatus.OK);
	}
}
