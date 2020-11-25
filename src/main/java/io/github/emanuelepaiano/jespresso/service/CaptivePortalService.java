package io.github.emanuelepaiano.jespresso.service;

import io.github.emanuelepaiano.jespresso.dto.captiveportal.AuthorizeDeviceRequestDTO;
import io.github.emanuelepaiano.jespresso.dto.captiveportal.SessionInfoDTO;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;

/**
 * The Interface CaptivePortalService.
 */
public interface CaptivePortalService {
	
	/**
	 * Authorize device.
	 *
	 * @param request the request
	 * @return the session info DTO
	 * @throws Exception 
	 * @throws NoContentException the not found exception
	 */
	public SessionInfoDTO authorizeDevice(AuthorizeDeviceRequestDTO request) throws Exception;
	
	
	/**
	 * Gets the session info.
	 *
	 * @param macAddress the mac address
	 * @return the session info
	 * @throws NoContentException the not found exception
	 */
	public SessionInfoDTO getSessionInfo(String macAddress) throws NoContentException;

}
