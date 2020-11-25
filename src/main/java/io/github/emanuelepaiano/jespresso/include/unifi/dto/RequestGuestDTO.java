package io.github.emanuelepaiano.jespresso.include.unifi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class RequestStaDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestGuestDTO {
	
	/** The cmd. */
	@NotNull @NotEmpty @NotBlank
	private String cmd;
	
	/** The mac address */
	@NotNull @NotEmpty @NotBlank
	private String mac;
	
}
