package io.github.emanuelepaiano.jespresso.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.github.emanuelepaiano.jespresso.entity.AdminUser;
import io.github.emanuelepaiano.jespresso.entity.Privilege;
import io.github.emanuelepaiano.jespresso.entity.Role;
import io.github.emanuelepaiano.jespresso.exception.NoContentException;
import io.github.emanuelepaiano.jespresso.repository.RoleRepository;
import io.github.emanuelepaiano.jespresso.service.AdministratorService;


/**
 * The Class JwtUserDetailsService.
 */
@Component
public class JwtUserDetailsService implements UserDetailsService {

	/** The administrator service. */
	@Autowired
	private AdministratorService administratorService;
	
	/** The role repository. */
	@Autowired
    private RoleRepository roleRepository;
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			AdminUser administrator = this.administratorService.getAdministratorByEmail(username);
			return new User(administrator.getEmail(), administrator.getPassword(), true, true, true, true, 
					this.getAuthorities(administrator.getRoles()));
		} catch (NoContentException e) {
			return new org.springframework.security.core.userdetails.User(
		              " ", " ", true, true, true, true, 
		              getAuthorities(Arrays.asList(
		                roleRepository.findByName("ROLE_USER").get())));
		}
	}
	
	/**
	 * Gets the authorities.
	 *
	 * @param roles the roles
	 * @return the authorities
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(
		      Collection<Role> roles) {
		  
		        return getGrantedAuthorities(getPrivileges(roles));
		    }
		 
		    /**
    		 * Gets the privileges.
    		 *
    		 * @param roles the roles
    		 * @return the privileges
    		 */
    		private List<String> getPrivileges(Collection<Role> roles) {
		  
		        List<String> privileges = new ArrayList<>();
		        List<Privilege> collection = new ArrayList<>();
		        for (Role role : roles) {
		            collection.addAll(role.getPrivileges());
		        }
		        for (Privilege item : collection) {
		            privileges.add(item.getName());
		        }
		        return privileges;
		    }
		 
		    /**
    		 * Gets the granted authorities.
    		 *
    		 * @param privileges the privileges
    		 * @return the granted authorities
    		 */
    		private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		        List<GrantedAuthority> authorities = new ArrayList<>();
		        for (String privilege : privileges) {
		            authorities.add(new SimpleGrantedAuthority(privilege));
		        }
		        return authorities;
		    }
}
