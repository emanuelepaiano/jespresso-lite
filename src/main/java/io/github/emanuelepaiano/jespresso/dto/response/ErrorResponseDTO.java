package io.github.emanuelepaiano.jespresso.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class ErrorResponseDTO.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ErrorResponseDTO extends GenericResponseDTO {

	/** The error description. */
	@JsonProperty("payload")
	private String errorDescription;

	
	/**
	 * Instantiates a new error response DTO.
	 *
	 * @param responseId the response id
	 * @param responseDescription the response description
	 * @param errorDescription the error description
	 */
	public ErrorResponseDTO(Integer responseId, String responseDescription, String errorDescription) {
		super(responseId, responseDescription);
		this.errorDescription = errorDescription;
	}
		
}