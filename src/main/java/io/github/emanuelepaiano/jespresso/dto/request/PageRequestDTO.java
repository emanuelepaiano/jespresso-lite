package io.github.emanuelepaiano.jespresso.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class PageRequestDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
	
	/** The page number. */
	private Integer pageNumber;
	
	/** The page limit. */
	private Integer pageLimit;
	
}
