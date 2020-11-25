package io.github.emanuelepaiano.jespresso.mock;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.include.unifi.UnifiApiClient;

/**
 * The Class UnifiApiClientMock. Mocking Unifi client for testing
 */
@ConditionalOnProperty(name = "unifiApi.controller.mock", havingValue="true", matchIfMissing = true)
@Service
public class UnifiApiClientMock implements UnifiApiClient {

	/**
	 * Login.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean login() {
		return true;
	}

	/**
	 * Logout.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean logout() {
		return true;
	}

	/**
	 * Authorize guest.
	 *
	 * @param macAddress the mac address
	 * @param minutes the minutes
	 * @param downloadSpeed the download speed
	 * @param uploadSpeed the upload speed
	 * @param quota the quota
	 * @return true, if successful
	 */
	@Override
	public boolean authorizeGuest(String macAddress, Long minutes, Long downloadSpeed, Long uploadSpeed, Long quota) {
		return true;
	}

	/**
	 * Un authorize guest.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	@Override
	public boolean unAuthorizeGuest(String macAddress) {
		return true;
	}

	/**
	 * Block.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	@Override
	public boolean block(String macAddress) {
		return true;
	}

	/**
	 * Unblock.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	@Override
	public boolean unblock(String macAddress) {
		return true;
	}

}
