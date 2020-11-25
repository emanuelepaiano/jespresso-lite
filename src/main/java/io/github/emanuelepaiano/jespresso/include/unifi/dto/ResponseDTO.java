package io.github.emanuelepaiano.jespresso.include.unifi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ResponseDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
	
	/** The meta. */
	private MetaDTO meta;
	
	/** The data. */
	private List<Object> data;


}
