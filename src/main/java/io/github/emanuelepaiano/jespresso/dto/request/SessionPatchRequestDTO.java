package io.github.emanuelepaiano.jespresso.dto.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The Class SessionPatchRequest.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionPatchRequestDTO {
	
	/** The expire session on. */
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp expireSessionOn;
	
	/** The remove session on. */
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp removeSessionOn;
}
