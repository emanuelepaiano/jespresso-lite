package io.github.emanuelepaiano.jespresso.service;

import java.util.List;

import io.github.emanuelepaiano.jespresso.dto.item.AdministratorDTO;
import io.github.emanuelepaiano.jespresso.entity.AdminUser;
import io.github.emanuelepaiano.jespresso.exception.AlreadyExistsException;
import io.github.emanuelepaiano.jespresso.exception.InvalidOperationException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;


/**
 * The Interface AdministratorService.
 */
public interface AdministratorService {
	
	/**
	 * Save administrator.
	 *
	 * @param administrator the administrator
	 * @param isCreate the is create
	 * @return the admin user
	 * @throws NoContentException the no content exception
	 * @throws AlreadyExistsException 
	 */
	public AdminUser saveAdministrator(AdminUser administrator, boolean isCreate) throws NoContentException, AlreadyExistsException;	
	
	/**
	 * Gets the administrators.
	 *
	 * @return the administrators
	 */
	public List<AdminUser> getAdministrators();
	
	/**
	 * Gets the administrator by id.
	 *
	 * @param id the id
	 * @return the administrator by id
	 * @throws NoContentException the no content exception
	 */
	public AdminUser getAdministratorById(Long id) throws NoContentException;
	
	
	/**
	 * Gets the administrator by email.
	 *
	 * @param email the email
	 * @return the administrator by email
	 * @throws NoContentException the no content exception
	 */
	public AdminUser getAdministratorByEmail(String email) throws NoContentException;
	
	/**
	 * To administrator DTO list.
	 *
	 * @param administrators the administrators
	 * @return the list
	 */
	public List<AdministratorDTO> toAdministratorDTOList(List<AdminUser> administrators);
	
	/**
	 * To administrator DTO list.
	 *
	 * @param administrators the administrators
	 * @return the list
	 */
	public AdministratorDTO toAdministratorDTO(AdminUser administrator);
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the administrator DTO
	 * @throws NoContentException the no content exception
	 * @throws InvalidOperationException the invalid operation exception
	 */
	public AdministratorDTO deleteById(Long id) throws NoContentException, InvalidOperationException;
	
	
	/**
	 * Change password.
	 *
	 * @param id the id
	 * @param password the password
	 * @return the administrator DTO
	 * @throws NoContentException the no content exception
	 * @throws AlreadyExistsException never occurs in this method
	 */
	public AdministratorDTO changePassword(Long id, String password) throws NoContentException, AlreadyExistsException;
	
}
