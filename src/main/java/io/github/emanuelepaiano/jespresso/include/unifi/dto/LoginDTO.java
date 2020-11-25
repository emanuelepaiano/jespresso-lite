package io.github.emanuelepaiano.jespresso.include.unifi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class LoginDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
}
