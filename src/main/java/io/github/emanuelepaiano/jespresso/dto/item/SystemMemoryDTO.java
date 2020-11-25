package io.github.emanuelepaiano.jespresso.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class SystemMemoryDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemMemoryDTO {
	
	/** The total. */
	private Long total;
	
	/** The free. */
	private Long free;
	
	/** The used. */
	private Long used;
	
	/** The max. */
	private Long max;
	
	/** The fsfree. */
	private Long fsfree;
	
	/** The fstotal. */
	private Long fstotal;

}
