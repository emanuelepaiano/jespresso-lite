package io.github.emanuelepaiano.jespresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.emanuelepaiano.jespresso.security.JwtRequest;
import io.github.emanuelepaiano.jespresso.security.JwtResponse;
import io.github.emanuelepaiano.jespresso.security.JwtTokenUtil;
import io.github.emanuelepaiano.jespresso.security.JwtUserDetailsService;

/**
 * The Class JwtAuthenticationController.
 */
@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	/** The user details service. */
	@Autowired
	private JwtUserDetailsService userDetailsService;

	/**
	 * Creates the authentication token.
	 *
	 * @param authenticationRequest the authentication request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	/**
	 * Authenticate.
	 *
	 * @param username the username
	 * @param password the password
	 * @throws Exception the exception
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
