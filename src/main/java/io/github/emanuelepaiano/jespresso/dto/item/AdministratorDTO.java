package io.github.emanuelepaiano.jespresso.dto.item;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class AdministratorOutDTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorDTO {
	
	/** The id. */
    private Long id;
    
    /** The username. */
    private String email;
    
    /** The email. */
    private String fullName;
	
	/** The creation date. */
	private Timestamp creationDate;
	
	/** The last modification. */
	private Timestamp lastModification;
	
	/** The enabled. */
	private Boolean enabled;

}
