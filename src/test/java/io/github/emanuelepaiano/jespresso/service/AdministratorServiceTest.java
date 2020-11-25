package io.github.emanuelepaiano.jespresso.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.emanuelepaiano.jespresso.entity.AdminUser;
import io.github.emanuelepaiano.jespresso.entity.MockEntity;
import io.github.emanuelepaiano.jespresso.entity.Role;
import io.github.emanuelepaiano.jespresso.exception.AlreadyExistsException;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.repository.AdminUserRepository;
import io.github.emanuelepaiano.jespresso.service.impl.AdministratorServiceImpl;
import io.github.emanuelepaiano.jespresso.service.impl.RoleServiceImpl;

public class AdministratorServiceTest {
	
	/** The administrator repository. */
	@Mock
	private AdminUserRepository repository;
	
	@Mock
	private RoleServiceImpl roleService;
	
	@Mock
	private PasswordEncoder bCryptPasswordEncoder;
	
	@InjectMocks
	private AdministratorServiceImpl service;
	
	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveAdministratorCreationTest() throws NoContentException, AlreadyExistsException {
		AdminUser entity = MockEntity.generateAdminUser();
		Role role = MockEntity.generateRole();
		role.setName("ROLE_ADMIN");
		when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
		when(roleService.getRoleByName(Mockito.anyString())).thenReturn(role);
		when(repository.save(MockEntity.generateAdminUser())).thenReturn(entity);
		when(bCryptPasswordEncoder.encode(Mockito.any())).thenReturn("test");
		assertEquals(entity, service.saveAdministrator(entity, true));
	}
	
	@Test
	public void saveAdministratorUpdatingTest() throws NoContentException, AlreadyExistsException {
		AdminUser entity = MockEntity.generateAdminUser();
		Role role = MockEntity.generateRole();
		role.setName("ROLE_ADMIN");
		when(repository.findById(Mockito.any())).thenReturn(Optional.of(entity));
		when(repository.save(MockEntity.generateAdminUser())).thenReturn(entity);
		assertEquals(entity, service.saveAdministrator(entity, false));
	}

}
