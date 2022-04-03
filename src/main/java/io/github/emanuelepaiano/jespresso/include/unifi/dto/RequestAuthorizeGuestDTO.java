package io.github.emanuelepaiano.jespresso.include.unifi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class RequestAuthorizeGuestDTO.
 */
public class RequestAuthorizeGuestDTO extends RequestGuestDTO {
	
	/** The minutes. */
	private Long minutes;
	
	/** The up. */
	private Long up;
	
	/** The down. */
	private Long down;
	
	/** The bytes. */
	private Long bytes;
	
	
	/** The ap mac. */
	@JsonProperty("ap_mac")
	private String apMac;

	/**
	 * Gets the ap mac.
	 *
	 * @return the ap mac
	 */
	public String getApMac() {
		return apMac;
	}

	
	/**
	 * Sets the ap mac.
	 *
	 * @param apMac the new ap mac
	 */
	public void setApMac(String apMac) {
		this.apMac = apMac;
	}

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
	 * @param mac the mac
	 * @param minutes the minutes
	 * @param up the up
	 * @param down the down
	 * @param bytes the bytes
	 * @param apMac the ap mac
	 */
	public RequestAuthorizeGuestDTO(String mac, Long minutes, Long up, Long down, Long bytes, String apMac) {
		super("authorize-guest", mac);
		this.minutes = minutes;
		this.up = up;
		this.down = down;
		this.bytes = bytes;
		this.apMac = apMac;
	}


	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "RequestAuthorizeGuestDTO [minutes=" + minutes + ", up=" + up + ", down=" + down + ", bytes=" + bytes
				+ ", apMac=" + apMac + "]";
	}

	
	
}
