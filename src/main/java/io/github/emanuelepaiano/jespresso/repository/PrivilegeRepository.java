package io.github.emanuelepaiano.jespresso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.emanuelepaiano.jespresso.entity.Privilege;

/**
 * The Interface PrivilegeRepository.
 */
@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>  {

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the privilege
	 */
	Optional<Privilege> findByName(String name);

}
