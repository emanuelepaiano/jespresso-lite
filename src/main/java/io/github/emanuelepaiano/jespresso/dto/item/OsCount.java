package io.github.emanuelepaiano.jespresso.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class OsCountDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsCount {
	
	/** The browser. */
	@JsonProperty("os")
	private String os;
	
	/** The quantity. */
	private Integer quantity;

}
