package io.github.emanuelepaiano.jespresso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Setting.
 */
@Entity
@Table(name="settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setting {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Min(1)
	private Long id;
	
	/** The name. */
	@Column
	@NotNull @NotBlank @NotEmpty
	private String name;
	
	/** The description. */
	@Column
	@NotNull @NotBlank @NotEmpty
	private String description;
	
	/** The type. */
	@NotNull
	private String type;
	
	/** The value. */
	@Column
	@NotNull
	private String value;

}
