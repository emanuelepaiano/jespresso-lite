package io.github.emanuelepaiano.jespresso.dto.item;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class AccessLogDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessLogDTO {

	/** The id. */
	private Long id;
	
	/** The device mac. */
	@NotNull @NotBlank @NotEmpty
	private String deviceMac;
	
	/** The device ip. */
	@NotNull @NotBlank @NotEmpty
	private String deviceIp;
	
	/** The accesspoint mac. */
	@NotNull @NotBlank @NotEmpty
	private String accesspointMac;
	
	/** The last login date. */
	@NotNull
	private Date lastLoginOn;
	
	/** The expire login date. */
	@NotNull
	private Date expireLoginOn;
	
	/** The remove session date. */
	@NotNull
	private Date removeSessionOn;
	
	/** The browser. */
	private String browser;
	
	/** The operating system. */
	private String operatingSystem;

	
}
