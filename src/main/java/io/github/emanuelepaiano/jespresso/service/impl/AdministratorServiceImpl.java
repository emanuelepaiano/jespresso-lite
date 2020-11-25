package io.github.emanuelepaiano.jespresso.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.dto.item.AdministratorDTO;
import io.github.emanuelepaiano.jespresso.entity.AdminUser;
import io.github.emanuelepaiano.jespresso.entity.Role;
import io.github.emanuelepaiano.jespresso.exception.AlreadyExistsException;
import io.github.emanuelepaiano.jespresso.exception.InvalidOperationException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.mapper.AdministratorMapper;
import io.github.emanuelepaiano.jespresso.repository.AdminUserRepository;
import io.github.emanuelepaiano.jespresso.service.AdministratorService;
import io.github.emanuelepaiano.jespresso.service.RoleService;
import io.github.emanuelepaiano.jespresso.util.LoggerConstants;

/**
 * The Class AdministratorServiceImpl.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

	/** The logger. */
	private static Logger logger = Logger.getLogger(AdministratorServiceImpl.class);

	/** The b crypt password encoder. */
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	/** The administrator repository. */
	@Autowired
	private AdminUserRepository administratorRepository;

	/** The role service. */
	@Autowired
	private RoleService roleService;

	/**
	 * Save administrator.
	 *
	 * @param administrator the administrator
	 * @param isCreate      the is create
	 * @return the admin user
	 * @throws NoContentException     the no content exception
	 * @throws AlreadyExistsException the already exists exception
	 */
	@Override
	public AdminUser saveAdministrator(AdminUser administrator, boolean isCreate)
			throws NoContentException, AlreadyExistsException {
		if (isCreate) {
			administrator = createAdministrator(administrator);
		} else {
			updateAdministrator(administrator);
		}

		return administratorRepository.save(administrator);
	}

	
	/**
	 * Update administrator.
	 *
	 * @param administrator the administrator
	 * @throws NoContentException the no content exception
	 */
	private void updateAdministrator(AdminUser administrator) throws NoContentException {
		AdminUser savedAdmin = getAdministratorById(administrator.getId());

		// if others administrators are disabled, enable this one (you need at least an
		// administrator)
		setEnableTrueIfOthersDisabled(administrator);

		// we don't want to overwrite stored password
		String encryptedPassword = savedAdmin.getPassword();
		administrator.setPassword(encryptedPassword);

		// We don't want to overwrite stored email
		administrator.setEmail(savedAdmin.getEmail());

		administrator.setLastModification(new Timestamp(System.currentTimeMillis()));
	}

	/**
	 * Creates the administrator.
	 *
	 * @param administrator the administrator
	 * @return the admin user
	 * @throws AlreadyExistsException the already exists exception
	 * @throws NoContentException the no content exception
	 */
	private AdminUser createAdministrator(AdminUser administrator) throws AlreadyExistsException, NoContentException {
		throwIfAlreadyExists(administrator);
		administrator = assignRole(administrator, "ROLE_ADMIN");
		administrator.setCreationDate(new Timestamp(System.currentTimeMillis()));
		administrator.setLastModification(new Timestamp(System.currentTimeMillis()));
		administrator = encryptAndSetPassword(administrator, administrator.getPassword());
		return administrator;
	}

	/**
	 * Assign admin role.
	 *
	 * @param administrator the administrator
	 * @param roleName the role name
	 * @return the admin usergetAdministratorByEmail
	 * @throws NoContentException the no content exception
	 */
	private AdminUser assignRole(AdminUser administrator, String roleName) throws NoContentException {
		Role role = roleService.getRoleByName(roleName);
		Collection<Role> userRoles = administrator.getRoles() != null ? administrator.getRoles() : new ArrayList<>();
		userRoles.add(role);
		administrator.setRoles(userRoles);
		return administrator;
	}

	/**
	 * Throw if already exists.
	 *
	 * @param administrator the administrator
	 * @throws AlreadyExistsException the already exists exception
	 */
	private void throwIfAlreadyExists(AdminUser administrator) throws AlreadyExistsException {
		if (existsAdministratorByEmail(administrator.getEmail())) {
			throw new AlreadyExistsException("administrator already exists", administrator.getEmail());
		}
	}

	/**
	 * Exists administrator by email.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	private boolean existsAdministratorByEmail(String email) {
		try {
			return getAdministratorByEmail(email) != null;
		} catch (NoContentException e) {
			return false;
		}
	}

	/**
	 * Sets the enable true if others disabled.
	 *
	 * @param administrator the new enable true if others disabled
	 */
	private void setEnableTrueIfOthersDisabled(AdminUser administrator) {
		if (!administrator.getEnabled()) {
			if (!thereIsAtLeastOneEnabledAdminExcluding(Arrays.asList(administrator))) {
				administrator.setEnabled(true);
			}
		}
	}

	/**
	 * Gets the enabled admins.
	 *
	 * @return the enabled admins
	 */
	private List<AdminUser> getEnabledAdmins() {
		return getAdministrators().stream().filter(admin -> admin.getEnabled()).collect(Collectors.toList());
	}

	/**
	 * There is at least one enabled admin excluding.
	 *
	 * @param isolatedAdmins the isolated admins
	 * @return true, if successful
	 */
	private boolean thereIsAtLeastOneEnabledAdminExcluding(List<AdminUser> isolatedAdmins) {
		// get All Enabled Administrators
		List<AdminUser> enabledAdmins = getEnabledAdmins();

		// remove some administrators
		isolatedAdmins.forEach(admin -> enabledAdmins.remove(admin));

		// check if there is at least one active Administrator
		return !enabledAdmins.isEmpty();
	}

	/**
	 * Encrypt and set password.
	 *
	 * @param administrator the administrator
	 * @param password      the password
	 * @return the admin user
	 */
	private AdminUser encryptAndSetPassword(AdminUser administrator, String password) {
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		logger.info("Password " + encryptedPassword + " generated for user " + administrator.getEmail());
		administrator.setPassword(encryptedPassword);
		return administrator;
	}

	/**
	 * Gets the administrators.
	 *
	 * @return the administrators
	 */
	public List<AdminUser> getAdministrators() {
		return this.administratorRepository.findAll();
	}

	/**
	 * Gets the administrator by id.
	 *
	 * @param id the id
	 * @return the administrator by id
	 * @throws NoContentException the no content exception
	 */
	@Override
	public AdminUser getAdministratorById(Long id) throws NoContentException {
		return administratorRepository.findById(id).orElseThrow(() -> {
			String message = String.format(LoggerConstants.NOT_FOUND_EXCEPTION, this.getClass().getName(),
					"getAdministratorById", "administrator", id);
			logger.error(message);
			return new NoContentException(message);
		});
	}

	/**
	 * To administrator DTO list.
	 *
	 * @param administrators the administrators
	 * @return the list
	 */
	@Override
	public List<AdministratorDTO> toAdministratorDTOList(List<AdminUser> administrators) {
		return this.administratorRepository.findAll().stream()
				.map(administrator -> AdministratorMapper.INSTANCE.toAdministratorOutDTO(administrator))
				.collect(Collectors.toList());
	}

	/**
	 * Change password.
	 *
	 * @param id       the id
	 * @param password the password
	 * @return the administrator DTO
	 * @throws NoContentException     the no content exception
	 * @throws AlreadyExistsException the already exists exception
	 */
	@Override
	public AdministratorDTO changePassword(Long id, String password) throws NoContentException, AlreadyExistsException {
		AdminUser administrator = encryptAndSetPassword(getAdministratorById(id), password);
		return AdministratorMapper.INSTANCE.toAdministratorOutDTO(saveAdministrator(administrator, false));
	}

	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the administrator DTO
	 * @throws NoContentException        the no content exception
	 * @throws InvalidOperationException the invalid operation exception
	 */
	@Override
	public AdministratorDTO deleteById(Long id) throws NoContentException, InvalidOperationException {
		AdministratorDTO administratorDTO = AdministratorMapper.INSTANCE
				.toAdministratorOutDTO(getAdministratorById(id));
		if (administratorRepository.findAll().size() < 2) {
			String message = String.format(LoggerConstants.INVALID_OPERATION_EXCEPTION,
					AdministratorServiceImpl.class.getCanonicalName(),
					"deleteById: cannot remove all administrators. You need al least one for administrator");
			logger.error(message);
			throw new InvalidOperationException("Cannot remove all administrators.");
		}
		String message = "removing administrator with ID = " + id;
		logger.info(message);
		administratorRepository.deleteById(administratorDTO.getId());
		return administratorDTO;
	}

	/**
	 * Gets the administrator by email.
	 *
	 * @param email the email
	 * @return the administrator by email
	 * @throws NoContentException the no content exception
	 */
	@Override
	public AdminUser getAdministratorByEmail(String email) throws NoContentException {
		return this.administratorRepository.findByEmail(email).orElseThrow(() -> {
			String message = String.format(LoggerConstants.NOT_FOUND_EXCEPTION, getClass().getName(),
					"getAdministratorByEmail", "administrator", email);
			logger.error(message);
			return new NoContentException(message);
		});
	}

	/**
	 * To administrator DTO.
	 *
	 * @param administrator the administrator
	 * @return the administrator DTO
	 */
	@Override
	public AdministratorDTO toAdministratorDTO(AdminUser administrator) {
		return AdministratorMapper.INSTANCE.toAdministratorOutDTO(administrator);
	}

}
