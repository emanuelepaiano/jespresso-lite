package io.github.emanuelepaiano.jespresso.security;

import java.io.Serializable;

/**
 * The Class JwtRequest.
 */
public class JwtRequest implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5926468583005150707L;

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;

	/**
	 * Instantiates a new jwt request.
	 */
	// need default constructor for JSON Parsing
	public JwtRequest() {
	}

	/**
	 * Instantiates a new jwt request.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
