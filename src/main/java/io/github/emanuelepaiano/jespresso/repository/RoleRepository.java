package io.github.emanuelepaiano.jespresso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.emanuelepaiano.jespresso.entity.Role;

/**
 * The Interface RoleRepository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Find by name.
	 *
	 * @param string the string
	 * @return the role
	 */
	Optional<Role> findByName(String string);

}
