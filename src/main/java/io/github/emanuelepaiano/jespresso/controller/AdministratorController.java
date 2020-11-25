package io.github.emanuelepaiano.jespresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.emanuelepaiano.jespresso.dto.item.AdministratorDTO;
import io.github.emanuelepaiano.jespresso.dto.response.SuccessResponseDTO;
import io.github.emanuelepaiano.jespresso.entity.AdminUser;
import io.github.emanuelepaiano.jespresso.exception.AlreadyExistsException;
import io.github.emanuelepaiano.jespresso.exception.InvalidOperationException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.mapper.AdministratorMapper;
import io.github.emanuelepaiano.jespresso.service.AdministratorService;

/**
 * The Class UserAdminController.
 */
@RestController
@RequestMapping("/api/admin")
public class AdministratorController {

	/** The administrator service. */
	@Autowired
	private AdministratorService administratorService;

	/**
	 * Sign up.
	 *
	 * @param administrator the administrator
	 * @param request       the request
	 * @return the administrator
	 * @throws NoContentException the no content exception
	 * @throws AlreadyExistsException 
	 */
	@PostMapping("/administrators")
	public ResponseEntity<Object> addAdministrator(@RequestBody AdminUser administrator)
			throws NoContentException, AlreadyExistsException {
		return new ResponseEntity<>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),
						administratorService
								.toAdministratorDTO(administratorService.saveAdministrator(administrator, true))),
				HttpStatus.OK);
	}

	/**
	 * Change password.
	 *
	 * @param password the password
	 * @param id       the id
	 * @return the response entity
	 * @throws NoContentException the no content exception
	 * @throws AlreadyExistsException 
	 */
	@PatchMapping("/administrators/{id}/password")
	public ResponseEntity<Object> changePassword(@RequestBody String password, @PathVariable("id") Long id)
			throws NoContentException, AlreadyExistsException {
		return new ResponseEntity<>(new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),
				administratorService.changePassword(id, password)), HttpStatus.OK);
	}

	/**
	 * Gets the administrators.
	 *
	 * @return the administrators
	 * @throws NoContentException
	 */
	@GetMapping("/administrators/{id}")
	public ResponseEntity<Object> getAdministrator(@PathVariable("id") Long id) throws NoContentException {
		return new ResponseEntity<>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),
						administratorService.toAdministratorDTO(administratorService.getAdministratorById(id))),
				HttpStatus.OK);
	}
	
	/**
	 * Gets the administrators.
	 *
	 * @return the administrators
	 * @throws NoContentException
	 */
	@GetMapping("/administrators/byusername/{username}")
	public ResponseEntity<Object> getAdministratorByEmail(@PathVariable("username") String username) throws NoContentException {
		return new ResponseEntity<>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),
						administratorService.toAdministratorDTO(administratorService.getAdministratorByEmail(username))),
				HttpStatus.OK);
	}

	/**
	 * Gets the administrators.
	 *
	 * @return the administrators
	 */
	@GetMapping("/administrators")
	public ResponseEntity<Object> getAdministrators() {
		return new ResponseEntity<>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),
						administratorService.toAdministratorDTOList(administratorService.getAdministrators())),
				HttpStatus.OK);
	}

	/**
	 * Edits the administrator.
	 *
	 * @param administratorDTO the administrator DTO
	 * @return the response entity
	 * @throws NoContentException the no content exception
	 * @throws AlreadyExistsException 
	 */
	@PutMapping("/administrators")
	public ResponseEntity<Object> editAdministrator(@RequestBody AdministratorDTO administratorDTO)
			throws NoContentException, AlreadyExistsException {
		return new ResponseEntity<>(
				new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),
						administratorService.toAdministratorDTO(administratorService.saveAdministrator(
								AdministratorMapper.INSTANCE.toAdministrator(administratorDTO), false))),
				HttpStatus.OK);
	}

	/**
	 * Delete administrator.
	 *
	 * @param id the id
	 * @return the administrator out DTO
	 * @throws NoContentException        the no content exception
	 * @throws InvalidOperationException
	 */
	@DeleteMapping("/administrators/{id}")
	public ResponseEntity<Object> deleteAdministrator(@PathVariable("id") Long id)
			throws NoContentException, InvalidOperationException {
		return new ResponseEntity<>(new SuccessResponseDTO(HttpStatus.OK.value(), HttpStatus.OK.name(),
				administratorService.deleteById(id)), HttpStatus.OK);
	}
}
