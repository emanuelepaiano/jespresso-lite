package io.github.emanuelepaiano.jespresso.service.impl;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.dto.captiveportal.AuthorizeDeviceRequestDTO;
import io.github.emanuelepaiano.jespresso.dto.captiveportal.SessionInfoDTO;
import io.github.emanuelepaiano.jespresso.entity.Session;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.include.unifi.UnifiApiClient;
import io.github.emanuelepaiano.jespresso.service.CaptivePortalService;
import io.github.emanuelepaiano.jespresso.service.SessionService;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;

/**
 * The Class CaptivePortalServiceImpl.
 */
@Service
public class CaptivePortalServiceImpl implements CaptivePortalService {

	/** The logger. */
	private final Logger logger = Logger.getLogger(CaptivePortalServiceImpl.class);

	/** The session service. */
	@Autowired
	private SessionService sessionService;

	/** The unifi api client. */
	@Autowired
	private UnifiApiClient unifiApiClient;

	/** The session duration minutes. */
	@Value("${unifiApi.controller.session.duration}")
	private Long sessionDurationMinutes;

	/** The download speed. */
	@Value("${unifiApi.controller.session.downloadSpeed}")
	private Long downloadSpeed;

	/** The upload speed. */
	@Value("${unifiApi.controller.session.uploadSpeed}")
	private Long uploadSpeed;

	/** The quota. */
	@Value("${unifiApi.controller.session.quota}")
	private Long quota;

	/** The session hidden minutes. */
	@Value("${unifiApi.controller.session.hiddenMinutes}")
	private Long sessionHiddenMinutes;

	/** The session block minutes. */
	@Value("${unifiApi.controller.session.blockMinutes}")
	private Long sessionBlockMinutes;

	/**
	 * Authorize device.
	 *
	 * @param request the request
	 * @return the session info DTO
	 * @throws Exception the exception
	 */
	@Override
	public SessionInfoDTO authorizeDevice(AuthorizeDeviceRequestDTO request) throws Exception {
		if (this.sessionService.existsByDeviceMac(request.getMacAddress())) {
			return this.getSessionInfo(request.getMacAddress());
		}

		if (unifiApiClient.authorizeGuest(request.getMacAddress(), sessionDurationMinutes, downloadSpeed, uploadSpeed,
				quota, request.getAccessPointMacAddress())) {
			Session session = generateSession(request.getMacAddress(), request.getIpAddress(),
					request.getAccessPointMacAddress(), request.getBrowser(), request.getOperatingSystem());
			return generateSessionInfo(sessionService.addSession(session));
		} else {
			throw new RuntimeException("Unifi Controller has not authorized guest for request " + request.toString());
		}
	}

	/**
	 * Gets the session info.
	 *
	 * @param macAddress the mac address
	 * @return the session info
	 * @throws NoContentException the not found exception
	 */
	@Override
	public SessionInfoDTO getSessionInfo(String macAddress) throws NoContentException {
		try {
			Session session = sessionService.findByDeviceMac(macAddress);
			return generateSessionInfo(session);
		} catch (NoContentException e) {
			String error = String.format(LoggerConstants.NOT_FOUND_EXCEPTION, "CaptivePortalServiceImpl",
					"getSessionInfo", "session", "");
			logger.error(error);
			throw e;
		}
	}

	/**
	 * Generate session info.
	 *
	 * @param session the session
	 * @return the session info DTO
	 */
	private SessionInfoDTO generateSessionInfo(Session session) {
		Timestamp loginDate = session.getLastLoginOn();
		Timestamp expireDate = session.getExpireLoginOn();
		Timestamp now = new Timestamp(System.currentTimeMillis());

		long diff = expireDate.getTime() - now.getTime();
		if (diff <= 0) {
			return new SessionInfoDTO(session.getDeviceMac(), Long.valueOf(0), Long.valueOf(0), expireDate, loginDate);
		}
		long diffSeconds = (diff / 1000) % 60;
		long diffMinutes = (diff / (60 * 1000));
		return new SessionInfoDTO(session.getDeviceMac(), Long.valueOf(diffMinutes), Long.valueOf(diffSeconds), expireDate, loginDate);
	}

	/**
	 * Generate session.
	 *
	 * @param macAddress    the mac address
	 * @param minutes       the minutes
	 * @param downloadSpeed the download speed
	 * @param uploadSpeed   the upload speed
	 * @param quota         the quota
	 * @param apMacAddress  the ap mac address
	 * @return the session
	 */
	private Session generateSession(String macAddress, String ipAddress, String accessPointMac, String browser,
			String operatingSystem) {
		Session session = new Session();
		session.setBrowser(browser);
		session.setOperatingSystem(operatingSystem);
		session.setDeviceMac(macAddress);
		session.setAccesspointMac(accessPointMac);
		session.setDeviceIp(ipAddress);
		Integer durationMinutes = this.sessionDurationMinutes != null ? this.sessionDurationMinutes.intValue() : 0;
		
		Timestamp expireDate = new Timestamp(System.currentTimeMillis()); // Timestamp.now();
		
		expireDate.setTime(expireDate.getTime() + TimeUnit.MINUTES
				.toMillis(this.sessionHiddenMinutes != null ? durationMinutes - sessionHiddenMinutes.intValue()
						: durationMinutes));

		Integer blockMinutes = this.sessionBlockMinutes != null ? this.sessionBlockMinutes.intValue() : 0;
		
		Timestamp unblockDate = new Timestamp(System.currentTimeMillis());
		
		unblockDate.setTime(expireDate.getTime() + TimeUnit.MINUTES.toMillis(blockMinutes));
		session.setExpireLoginOn(expireDate);
		session.setLastLoginOn(new Timestamp(System.currentTimeMillis())); // now()
		session.setRemoveSessionOn(unblockDate);
		return session;
	}

}
