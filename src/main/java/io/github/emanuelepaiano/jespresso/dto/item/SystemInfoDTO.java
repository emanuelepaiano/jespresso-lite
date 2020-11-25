package io.github.emanuelepaiano.jespresso.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class SystemInfoDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemInfoDTO {
	
	/** The hostname. */
	private String hostname;
	
	/** The ip address. */
	private String ipAddress;
	
	/** The operating system. */
	private String operatingSystem;
	
	/** The operating system version. */
	private String operatingSystemVersion;
	
	/** The java version. */
	private String javaVersion;
	
	/** The java vendor. */
	private String javaVendor;
	
	/** The os arch. */
	private String osArch;
}
	