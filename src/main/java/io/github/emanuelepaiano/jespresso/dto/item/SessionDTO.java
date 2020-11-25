package io.github.emanuelepaiano.jespresso.dto.item;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class SessionDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
	
	/** The id. */
	private Long id;
	
	/** The device mac. */
	@NotNull @NotBlank @NotNull
	private String deviceMac;
	
	/** The device ip. */
	@NotNull @NotBlank @NotNull
	private String deviceIp;
	
	/** The accesspoint mac. */
	@NotNull @NotBlank @NotNull
	private String accesspointMac;
	
	/** The last login date. */
	@NotNull
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp lastLoginOn;
	
	/** The expire login date. */
	@NotNull
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp expireLoginOn;
	
	/** The remove session date. */
	@NotNull
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp removeSessionOn;
	
	/** The browser. */
	private String browser;
	
	/** The operating system. */
	private String operatingSystem;
	

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	@JsonProperty("valid")
	public boolean isValid() {
		Timestamp now = new Timestamp((new Date()).getTime());
		return expireLoginOn.after(now);
	}
	
	
}
