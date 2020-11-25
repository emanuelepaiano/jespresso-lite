package io.github.emanuelepaiano.jespresso.include.unifi.dto;

/**
 * The Class RequestAuthorizeGuestDTO.
 */
public class RequestAuthorizeGuestDTO extends RequestGuestDTO {
	
	/** The minutes. */
	private Long minutes;
	
	/**  upload. */
	private Long up;
	
	/**  download. */
	private Long down;
	
	/** bytes. */
	private Long bytes;

	/**
	 * Gets the minutes.
	 *
	 * @return the minutes
	 */
	public Long getMinutes() {
		return minutes;
	}

	/**
	 * Sets the minutes.
	 *
	 * @param minutes the new minutes
	 */
	public void setMinutes(Long minutes) {
		this.minutes = minutes;
	}

	/**
	 * Gets the up.
	 *
	 * @return the up
	 */
	public Long getUp() {
		return up;
	}

	/**
	 * Sets the up.
	 *
	 * @param up the new up
	 */
	public void setUp(Long up) {
		this.up = up;
	}

	/**
	 * Gets the down.
	 *
	 * @return the down
	 */
	public Long getDown() {
		return down;
	}

	/**
	 * Sets the down.
	 *
	 * @param down the new down
	 */
	public void setDown(Long down) {
		this.down = down;
	}

	/**
	 * Gets the bytes.
	 *
	 * @return the bytes
	 */
	public Long getBytes() {
		return bytes;
	}

	/**
	 * Sets the bytes.
	 *
	 * @param bytes the new bytes
	 */
	public void setBytes(Long bytes) {
		this.bytes = bytes;
	}

	/**
	 * Instantiates a new request authorize guest DTO.
	 */
	public RequestAuthorizeGuestDTO() {
		super();
	}

	/**
	 * Instantiates a new request authorize guest DTO.
	 *
	 * @param mac the mac address
	 * @param minutes the minutes
	 * @param up the upload
	 * @param down the download
	 * @param bytes the bytes
	 */
	public RequestAuthorizeGuestDTO(String mac, Long minutes, Long up, Long down, Long bytes) {
		super("authorize-guest", mac);
		this.minutes = minutes;
		this.up = up;
		this.down = down;
		this.bytes = bytes;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "RequestAuthorizeGuestDTO [minutes=" + minutes + ", up=" + up + ", down=" + down + ", bytes=" + bytes
				+ "]";
	}
	
	
}
