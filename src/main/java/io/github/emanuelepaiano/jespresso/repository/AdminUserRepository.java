package io.github.emanuelepaiano.jespresso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.emanuelepaiano.jespresso.entity.AdminUser;

/**
 * The Interface AdminUserRepository.
 */
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long>{
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional<AdminUser> findByEmail(@Param("email") String email);
	
}
