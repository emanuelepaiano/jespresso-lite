package io.github.emanuelepaiano.jespresso.security;

import java.io.Serializable;

/**
 * The Class JwtResponse.
 */
public class JwtResponse implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8091879091924046844L;
	
	/** The jwttoken. */
	private final String jwttoken;

	/**
	 * Instantiates a new jwt response.
	 *
	 * @param jwttoken the jwttoken
	 */
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.jwttoken;
	}
}
