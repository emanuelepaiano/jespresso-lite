package io.github.emanuelepaiano.jespresso.startup_services;

import java.util.TimeZone;

import io.github.emanuelepaiano.jespresso.startup.StartupService;

/**
 * The Class DefaultTimeZone.
 */
public class DefaultTimeZone implements StartupService {

	/**
	 * Inits the.
	 */
	@Override
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}

}
