package io.github.emanuelepaiano.jespresso.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * The Class GenericResponseDTO.
 */
@Data
public class GenericResponseDTO {
	
	/** The response id. */
	 @JsonProperty("response")
	private Integer responseId;
	
	/** The response type. */
	 @JsonProperty("description")
	private String responseDescription;

	public GenericResponseDTO(Integer responseId, String responseDescription) {
		super();
		this.responseId = responseId;
		this.responseDescription = responseDescription;
	}
	
}
