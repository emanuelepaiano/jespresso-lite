package io.github.emanuelepaiano.jespresso.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.emanuelepaiano.jespresso.dto.item.BrowserCount;
import io.github.emanuelepaiano.jespresso.dto.item.OsCount;
import io.github.emanuelepaiano.jespresso.dto.response.SuccessResponseDTO;
import io.github.emanuelepaiano.jespresso.exception.BadRequestException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.service.InfoService;
import io.github.emanuelepaiano.jespresso.service.SessionService;
import io.github.emanuelepaiano.jespresso.service.SystemInfoService;
import io.github.emanuelepaiano.jespresso.util.HttpHelper;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;

/**
 * The Class InfoController.
 */
@RestController
@RequestMapping("/api/admin/info")
public class InfoController {

	/** The session service. */
	@Autowired
	private SessionService sessionService;
	
	/** The system info service. */
	@Autowired
	private SystemInfoService systemInfoService;

	/** The info service. */
	@Autowired
	private InfoService infoService;

	/** The logger. */
	private final Logger logger = Logger.getLogger(InfoController.class);

	/**
	 * Gets the sessions browser list.
	 *
	 * @return the sessions browser list
	 */
	@GetMapping("/sessions/browser-list")
	public ResponseEntity<Object> getSessionsBrowserList() {
		List<String> result = this.sessionService.getAvailableBrowsers();
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), result), HttpStatus.OK);
	}

	/**
	 * Gets the sessions browser count.
	 *
	 * @return the sessions browser count
	 */
	@GetMapping("/sessions/browser-count")
	public ResponseEntity<Object> getSessionsBrowserCount() {
		List<BrowserCount> result = this.infoService.getBrowsersCount();
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), result), HttpStatus.OK);
	}

	/**
	 * Gets the count expired sessions.
	 *
	 * @return the count expired sessions
	 */
	@GetMapping("/sessions/expired/count")
	public ResponseEntity<Object> getCountExpiredSessions() {
		Integer result = this.sessionService.getExpiredSessions().size();
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), result), HttpStatus.OK);
	}

	/**
	 * Gets the count valid sessions.
	 *
	 * @return the count valid sessions
	 */
	@GetMapping("/sessions/valid/count")
	public ResponseEntity<Object> getCountValidSessions() {
		Integer result = this.sessionService.getValidSessions().size();
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), result), HttpStatus.OK);
	}

	/**
	 * Gets the sessions count.
	 *
	 * @return the sessions count
	 * @throws NoContentException the no content exception
	 */
	@GetMapping("/sessions/count")
	public ResponseEntity<Object> getSessionsCount() throws NoContentException {
		Integer size = this.sessionService.getSessions().size();
		return new ResponseEntity<Object>(new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), size),
				HttpStatus.OK);
	}

	/**
	 * Gets the sessions os list.
	 *
	 * @return the sessions os list
	 */
	@GetMapping("/sessions/os-list")
	public ResponseEntity<Object> getSessionsOsList() {
		List<String> result = this.sessionService.getAvailableOS();
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), result), HttpStatus.OK);
	}

	/**
	 * Gets the sessions os count.
	 *
	 * @return the sessions os count
	 */
	@GetMapping("/sessions/os-count")
	public ResponseEntity<Object> getSessionsOsCount() {
		List<OsCount> result = this.infoService.getOsCount();
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), result), HttpStatus.OK);
	}

	/**
	 * Gets the last sessions.
	 *
	 * @param request the request
	 * @param paging the paging
	 * @param size the size
	 * @param page the page
	 * @param lastDays the last days
	 * @return the last sessions
	 * @throws BadRequestException the bad request exception
	 */
	@GetMapping("/sessions/last-login")
	public ResponseEntity<Object> getLastSessions(HttpServletRequest request,
			@RequestParam(name = "paging", required = false, defaultValue = "false") Boolean paging,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "lastDays", required = false, defaultValue = "365") Integer lastDays) throws BadRequestException {
		throwIfNotValidPageRequest(request, paging, Arrays.asList("paging", "size", "page", "lastDays"));
		return new ResponseEntity<Object>(new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(),
				this.infoService.getLastSessions(paging, size, page, lastDays)), HttpStatus.OK);
	}

	
	/**
	 * Throw if not valid page request.
	 *
	 * @param request the request
	 * @param paging the paging
	 * @param requiredParams the required params
	 * @throws BadRequestException the bad request exception
	 */
	private void throwIfNotValidPageRequest(HttpServletRequest request, Boolean paging, List<String> requiredParams) throws BadRequestException {
		if (paging && !HttpHelper.isValidParam(request, requiredParams)) {
			logger.error(String.format(LoggerConstants.INVALID_REST_QUERY, "SessionController", "getLastSessions()"));
			throw new BadRequestException(String
					.format(LoggerConstants.INVALID_REST_QUERY, "SessionController", "getLastSessions()"));
		}
	}
	
	/**
	 * Gets the system memory.
	 *
	 * @return the system memory
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@GetMapping("/system/memory")
	public ResponseEntity<Object> getSystemMemory() throws IOException {
		return new ResponseEntity<Object>(new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(),
				this.systemInfoService.getSystemMemory()), HttpStatus.OK);
	}
	
	/**
	 * Gets the system info.
	 *
	 * @return the system info
	 */
	@GetMapping("/system/info")
	public ResponseEntity<Object> getSystemInfo() {
		return new ResponseEntity<Object>(new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(),
				this.systemInfoService.getSystemInfo()), HttpStatus.OK);
	}

}
