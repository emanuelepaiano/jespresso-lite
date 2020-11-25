package io.github.emanuelepaiano.jespresso.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class SuccessResponseDTO.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SuccessResponseDTO extends GenericResponseDTO {
	
	/** The payload. */
	 @JsonProperty("payload")
	private Object payload;

	/**
	 * Instantiates a new success response DTO.
	 *
	 * @param responseId the response id
	 * @param responseDescription the response description
	 * @param payload the payload
	 */
	public SuccessResponseDTO(Integer responseId, String responseDescription, Object payload) {
		super(responseId, responseDescription);
		this.payload = payload;
	}
}
