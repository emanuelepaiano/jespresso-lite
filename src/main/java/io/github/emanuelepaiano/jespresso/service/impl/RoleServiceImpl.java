package io.github.emanuelepaiano.jespresso.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.entity.Role;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.repository.RoleRepository;
import io.github.emanuelepaiano.jespresso.service.RoleService;


/**
 * The Class RoleServiceImpl.
 */
@Service
public class RoleServiceImpl implements RoleService {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	/** The role repository. */
	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * Gets the role by name.
	 *
	 * @param name the name
	 * @return the role by name
	 * @throws NoContentException the no content exception
	 */
	@Override
	public Role getRoleByName(String name) throws NoContentException {
		Optional<Role> roleFromDb = roleRepository.findByName(name);
		if (roleFromDb.isPresent()) {
			return roleFromDb.get();
		} else {
			String message = String.format("No role %s found.", name);
			logger.error("%s: %s: ", "getRoleByName()", message);
			throw new NoContentException(message);
		}
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return this.roleRepository.findAll();
	}
	
}
