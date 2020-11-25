package io.github.emanuelepaiano.jespresso.service;

import io.github.emanuelepaiano.jespresso.entity.Role;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;

public interface RoleService {
	
	public Role getRoleByName(String name) throws NoContentException;
}
