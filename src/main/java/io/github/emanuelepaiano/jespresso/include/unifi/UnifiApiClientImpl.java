package io.github.emanuelepaiano.jespresso.include.unifi;

import org.apache.logging.log4j.util.Strings;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.emanuelepaiano.jespresso.include.unifi.dto.LoginDTO;
import io.github.emanuelepaiano.jespresso.include.unifi.dto.RequestAuthorizeGuestDTO;
import io.github.emanuelepaiano.jespresso.include.unifi.dto.RequestGuestDTO;
import io.github.emanuelepaiano.jespresso.include.unifi.dto.ResponseDTO;

/**
 * The Class UnifiApiClientImpl.
 */
@ConditionalOnProperty(name = "unifiApi.controller.mock", havingValue = "false", matchIfMissing = false)
@Service
public class UnifiApiClientImpl implements UnifiApiClient {

	/** The logger. */
	private static Logger logger = Logger.getLogger(UnifiApiClientImpl.class);

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;

	/** The cookie. */
	private String cookie = Strings.EMPTY;

	/** The is logged. */
	private boolean isLogged = false;

	/** The username. */
	private String username;

	/** The password. */
	private String password;

	/** The site name. */
	private String siteName;

	/** The base url. */
	private String baseUrl;

	/**
	 * Instantiates a new unifi api client impl.
	 *
	 * @param username the username
	 * @param password the password
	 * @param siteName the site name
	 * @param baseUrl  the base url
	 */
	public UnifiApiClientImpl(@Value("${unifiApi.controller.username}") String username,
			@Value("${unifiApi.controller.password}") String password,
			@Value("${unifiApi.controller.sitename}") String siteName,
			@Value("${unifiApi.controller.url}") String baseUrl) {
		super();
		this.username = username;
		this.password = password;
		this.siteName = siteName;
		this.baseUrl = baseUrl;
	}

	/**
	 * Finalize.
	 */
	public void finalize() {
		if (isLogged()) {
			this.logout();
		}
	}

	/**
	 * Gets the cookie.
	 *
	 * @return the cookie
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * Sets the cookie.
	 *
	 * @param cookie the new cookie
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the site name.
	 *
	 * @return the site name
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * Sets the site name.
	 *
	 * @param siteName the new site name
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * Gets the base url.
	 *
	 * @return the base url
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Sets the base url.
	 *
	 * @param baseUrl the new base url
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * Checks if is logged.
	 *
	 * @return true, if is logged
	 */
	public boolean isLogged() {
		return this.isLogged;
	}

	/**
	 * Login.
	 *
	 * @return true, if successful
	 */
	@Scheduled(fixedDelayString = "${jespresso.controller.cookie.update.delay}", initialDelay = 15000)
	@Override
	public boolean login() {
		try {
			if (this.isLogged) {
				return true;
			}
			ResponseEntity<ResponseDTO> response = this.restTemplate.postForEntity(this.baseUrl + "/api/login",
					new LoginDTO(this.username, this.password), ResponseDTO.class);
			logger.info("Unifi Controller returned " + response.toString());
			this.isLogged = response.getStatusCode().is2xxSuccessful()
					&& response.getBody().getMeta().getRc().equalsIgnoreCase("ok");
			this.setCookie(response.getHeaders().getFirst(HttpHeaders.SET_COOKIE));
			return this.isLogged;
		} catch (RuntimeException e) {
			logger.error(
					UnifiApiClientImpl.class.getName() + ": login(): cannot log in with controller: " + e.getMessage());
			throw new RuntimeException(
					UnifiApiClientImpl.class.getName() + ": login(): cannot login with controller: " + e.getMessage());
		}
	}

	/**
	 * Logout.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean logout() {
		try {
			if (!this.isLogged) {
				return true;
			}
			ResponseEntity<ResponseDTO> response = this.restTemplate.getForEntity(this.baseUrl + "/api/logout",
					ResponseDTO.class);
			logger.info("Unifi Controller returned " + response.toString());
			this.isLogged = response.getStatusCode().is2xxSuccessful()
					&& response.getBody().getMeta().getRc().equalsIgnoreCase("ok");
			this.setCookie(Strings.EMPTY);
			return this.isLogged;
		} catch (RuntimeException e) {
			logger.error(UnifiApiClientImpl.class.getName() + ": logout(): cannot log out with controller: "
					+ e.getMessage());
			throw new RuntimeException(UnifiApiClientImpl.class.getName()
					+ ": login(): cannot log out with controller: " + e.getMessage());
		}
	}

	/**
	 * Authorize guest.
	 *
	 * @param macAddress    the mac address
	 * @param minutes       the minutes
	 * @param downloadSpeed the download speed
	 * @param uploadSpeed   the upload speed
	 * @param quota         the quota
	 * @return true, if successful
	 */
	@Override
	public boolean authorizeGuest(String macAddress, Long minutes, Long downloadSpeed, Long uploadSpeed, Long quota, String apMac) {
		try {
			if (!this.isLogged) {
				login();
			}
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.add("Cookie", this.getCookie());
			RequestAuthorizeGuestDTO body = new RequestAuthorizeGuestDTO(macAddress, minutes, uploadSpeed,
					downloadSpeed, quota, apMac);
			HttpEntity<RequestAuthorizeGuestDTO> requestEntity = new HttpEntity<>(body, requestHeaders);
			String url = this.baseUrl + "/api/s/" + siteName + "/cmd/stamgr";
			ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					ResponseDTO.class);
			logger.info("Unifi Controller returned " + response.toString());
			return response.getStatusCode().is2xxSuccessful()
					&& response.getBody().getMeta().getRc().equalsIgnoreCase("ok");
		} catch (RuntimeException e) {
			logger.error(UnifiApiClientImpl.class.getName()
					+ ": authorizeGuest(): cannot send request to remote controller: " + e.getMessage());
			throw new RuntimeException(UnifiApiClientImpl.class.getName()
					+ ": authorizeGuest(): cannot send request to remote controller: " + e.getMessage());
		}
	}

	/**
	 * Un authorize guest.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	@Override
	public boolean unAuthorizeGuest(String macAddress) {
		try {
			if (!this.isLogged) {
				login();
			}
			String url = this.baseUrl + "/api/s/" + siteName + "/cmd/stamgr";
			ResponseEntity<ResponseDTO> response = sendRequestGuestAction(url, "unauthorize-guest", macAddress);
			logger.info("Unifi Controller returned " + response.toString());
			return response.getStatusCode().is2xxSuccessful()
					&& response.getBody().getMeta().getRc().equalsIgnoreCase("ok");
		} catch (RuntimeException e) {
			logger.error(UnifiApiClientImpl.class.getName()
					+ ": unauthorizeGuest(): cannot send request to remote controller: " + e.getMessage());
			throw new RuntimeException(UnifiApiClientImpl.class.getName()
					+ ": unauthorizeGuest(): cannot send request to remote controller: " + e.getMessage());
		}
	}

	/**
	 * Block.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	@Override
	public boolean block(String macAddress) {
		try {
			if (!this.isLogged) {
				login();
			}
			String url = this.baseUrl + "/api/s/" + siteName + "/cmd/stamgr";
			ResponseEntity<ResponseDTO> response = sendRequestGuestAction(url, "block-sta", macAddress);
			logger.info("Unifi Controller returned " + response.toString());
			return response.getStatusCode().is2xxSuccessful()
					&& response.getBody().getMeta().getRc().equalsIgnoreCase("ok");
		} catch (RuntimeException e) {
			logger.error(UnifiApiClientImpl.class.getName() + ": block(): cannot send request to remote controller: "
					+ e.getMessage());
			throw new RuntimeException(UnifiApiClientImpl.class.getName()
					+ ": block(): cannot send request to remote controller: " + e.getMessage());
		}
	}

	/**
	 * Unblock.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	@Override
	public boolean unblock(String macAddress) {
		try {
			if (!this.isLogged) {
				login();
			}
			String url = this.baseUrl + "/api/s/" + siteName + "/cmd/stamgr";
			ResponseEntity<ResponseDTO> response = sendRequestGuestAction(url, "unblock-sta", macAddress);
			logger.info("Unifi Controller returned " + response.toString());
			return response.getStatusCode().is2xxSuccessful()
					&& response.getBody().getMeta().getRc().equalsIgnoreCase("ok");
		} catch (RuntimeException e) {
			logger.error(UnifiApiClientImpl.class.getName() + ": unblock(): cannot send request to remote controller: "
					+ e.getMessage());
			throw new RuntimeException(UnifiApiClientImpl.class.getName()
					+ ": unblock(): cannot send request to remote controller: " + e.getMessage());
		}
	}

	/**
	 * Send request guest action.
	 *
	 * @param url        the url
	 * @param cmd        the cmd
	 * @param macAddress the mac address
	 * @return the response entity
	 */
	private ResponseEntity<ResponseDTO> sendRequestGuestAction(String url, String cmd, String macAddress) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("Cookie", this.getCookie());
		RequestGuestDTO body = new RequestGuestDTO(cmd, macAddress);
		HttpEntity<RequestGuestDTO> requestEntity = new HttpEntity<>(body, requestHeaders);
		ResponseEntity<ResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				ResponseDTO.class);
		return response;
	}

}
