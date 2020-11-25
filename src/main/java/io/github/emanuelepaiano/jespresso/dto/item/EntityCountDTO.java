package io.github.emanuelepaiano.jespresso.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class EntityCountDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityCountDTO {
	
	/** The entity name. */
	private String entityName;
	
	/** The count. */
	private Integer count;
	
	
}
