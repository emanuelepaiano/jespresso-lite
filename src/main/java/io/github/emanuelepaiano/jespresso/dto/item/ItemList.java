package io.github.emanuelepaiano.jespresso.dto.item;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class ItemListDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemList {
	
	/** The pages. */
	private Integer pages;
	
	/** The current page. */
	private Integer currentPage;
	
	/** The items. */
	private List<?> items;
}
