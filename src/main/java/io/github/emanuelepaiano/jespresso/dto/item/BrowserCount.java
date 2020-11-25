package io.github.emanuelepaiano.jespresso.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class BrowserCountDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrowserCount {
	
	/** The browser. */
	@JsonProperty("browserName")
	private String browser;
	
	/** The quantity. */
	private Integer quantity;

}
