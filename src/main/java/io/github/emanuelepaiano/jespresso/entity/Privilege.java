package io.github.emanuelepaiano.jespresso.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Privilege.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {
	
	/** The id. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    /** The name. */
    private String name;
 
    /** The roles. */
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
