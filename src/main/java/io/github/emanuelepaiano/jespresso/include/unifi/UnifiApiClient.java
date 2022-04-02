package io.github.emanuelepaiano.jespresso.include.unifi;


/**
 * The Interface UnifiApiClient.
 */
public interface UnifiApiClient {
	
	/**
	 * Login.
	 *
	 * @return true, if successful
	 */
	public boolean login();
	
	/**
	 * Logout.
	 *
	 * @return true, if successful
	 */
	public boolean logout();
	
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
	public boolean authorizeGuest(String macAddress, Long minutes, Long downloadSpeed, Long uploadSpeed, Long quota, String apClient);
	
	/**
	 * Un authorize guest.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	public boolean unAuthorizeGuest(String macAddress);
	
	
	/**
	 * Block.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	public boolean block(String macAddress);
	
	/**
	 * Unblock.
	 *
	 * @param macAddress the mac address
	 * @return true, if successful
	 */
	public boolean unblock(String macAddress);

}
