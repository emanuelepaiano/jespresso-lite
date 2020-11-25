package io.github.emanuelepaiano.jespresso.dto.captiveportal;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class SessionInfoDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfoDTO {

	/** The mac address. */
	private String macAddress;
	
	/** The minutes left. */
	private Long minutesLeft;
	
	/** The minutes left. */
	private Long secondsLeft;
	
	/** The expire on. */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp expireOn;
	
	/** The last login. */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp lastLogin;
	
	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		return (getMinutesLeft() > 0 || getSecondsLeft() > 10);
	}

}
