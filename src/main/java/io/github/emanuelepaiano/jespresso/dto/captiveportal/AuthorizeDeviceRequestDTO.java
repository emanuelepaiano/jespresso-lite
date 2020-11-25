package io.github.emanuelepaiano.jespresso.dto.captiveportal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class AuthorizeDeviceRequest for CaptivePortalController.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeDeviceRequestDTO {
	
	/** The mac address. */
	@NotNull @NotEmpty @NotBlank
	private String macAddress;

	
	/** The ip address. */
	private String ipAddress;
	
	/** The access point mac address. */
	private String accessPointMacAddress;
	
	/** The email. */
	@NotNull @NotEmpty @NotBlank
	private String email;
	
	
	/** The browser. */
	private String browser;
	
	/** The operating system. */
	private String operatingSystem;
	
	/** The accept tou. */
	private Boolean acceptTou = Boolean.FALSE;	
}
