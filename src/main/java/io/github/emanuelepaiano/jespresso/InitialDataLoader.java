package io.github.emanuelepaiano.jespresso;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.github.emanuelepaiano.jespresso.entity.AdminUser;
import io.github.emanuelepaiano.jespresso.entity.Privilege;
import io.github.emanuelepaiano.jespresso.entity.Role;
import io.github.emanuelepaiano.jespresso.repository.AdminUserRepository;
import io.github.emanuelepaiano.jespresso.repository.PrivilegeRepository;
import io.github.emanuelepaiano.jespresso.repository.RoleRepository;

/**
 * The Class InitialDataLoader.
 */
@ConditionalOnProperty(prefix = "jespresso.datasource.data", name = "initialize", matchIfMissing = false, havingValue = "true")
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	/** The already setup. */
	boolean alreadySetup = false;

	/** The admin user repository. */
	@Autowired
	private AdminUserRepository adminUserRepository;

	/** The role repository. */
	@Autowired
	private RoleRepository roleRepository;

	/** The privilege repository. */
	@Autowired
	private PrivilegeRepository privilegeRepository;

	/** The password encoder. */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * On application event.
	 *
	 * @param event the event
	 */
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		alreadySetup = !adminUserRepository.findAll().isEmpty();
		if (!alreadySetup) {
			Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
			Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

			List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
			createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
			createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

			Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
			AdminUser user = new AdminUser();
			user.setFullName("Administrator");
			user.setPassword(passwordEncoder.encode("password"));
			user.setEmail("admin@localhost");
			user.setRoles(Arrays.asList(adminRole));
			user.setEnabled(true);
			adminUserRepository.save(user);

			alreadySetup = true;
		}
	}

	/**
	 * Creates the privilege if not found.
	 *
	 * @param name the name
	 * @return the privilege
	 */
	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Optional<Privilege> privilegeFromDb = privilegeRepository.findByName(name);
		if (!privilegeFromDb.isPresent()) {
			Privilege privilege = new Privilege(null, name, null);
			return privilegeRepository.save(privilege);
		}
		return privilegeFromDb.get();
	}

	/**
	 * Creates the role if not found.
	 *
	 * @param name       the name
	 * @param privileges the privileges
	 * @return the role
	 */
	@Transactional
	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Optional<Role> roleFromDb = roleRepository.findByName(name);
		if (!roleFromDb.isPresent()) {
			Role role = new Role();
			role.setName(name);
			role.setPrivileges(privileges);
			return roleRepository.save(role);
		}
		return roleFromDb.get();
	}

}
