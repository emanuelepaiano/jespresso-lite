package io.github.emanuelepaiano.jespresso.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.emanuelepaiano.jespresso.dto.item.AccessLogDTO;
import io.github.emanuelepaiano.jespresso.dto.response.SuccessResponseDTO;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.mapper.AccessLogMapper;
import io.github.emanuelepaiano.jespresso.service.AccessLogService;

@RestController
@RequestMapping("/api/admin")
public class AccessLogController {

	@Autowired
	private AccessLogService accessLogService;

	/**
	 * Gets the accessLogs.
	 *
	 * @param request the request
	 * @return the accessLogs
	 * @throws NoContentException
	 */
	@GetMapping("/accessLogs")
	public ResponseEntity<Object> getAccessLogs(HttpServletRequest request,
			@RequestParam(name = "paging", required = false, defaultValue = "false") Boolean paging,
			@RequestParam(name = "size", required = false, defaultValue = "50") Integer size,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page)
			throws NoContentException {
		List<AccessLogDTO> accessLogsDTO = new ArrayList<AccessLogDTO>();
		this.accessLogService.getAccessLogs().forEach(accessLog -> {
			accessLogsDTO.add(AccessLogMapper.INSTANCE.accessLogToAccessLogDTO(accessLog));
		});
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), accessLogsDTO), HttpStatus.OK);
	}

	/**
	 * Gets the accessLog.
	 *
	 * @param id      the id
	 * @param request the request
	 * @return the accessLog
	 * @throws NoContentException
	 */
	@GetMapping("/accessLogs/{id}")
	public ResponseEntity<Object> getAccessLog(@PathVariable("id") Long id) throws NoContentException {
		AccessLogDTO accessLogDTO = AccessLogMapper.INSTANCE.accessLogToAccessLogDTO(accessLogService.getAccessLog(id));
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), accessLogDTO), HttpStatus.OK);
	}

	/**
	 * Delete accessLog.
	 *
	 * @param id      the id
	 * @param request the request
	 * @return the response entity
	 * @throws NoContentException
	 */
	@DeleteMapping("/accessLogs/{id}")
	public ResponseEntity<Object> deleteAccessLog(@PathVariable("id") Long id) throws NoContentException {
		boolean isDeleted = this.accessLogService.deleteAccessLog(id);
		return new ResponseEntity<Object>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.toString(), isDeleted), HttpStatus.OK);
	}
}
