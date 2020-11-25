package io.github.emanuelepaiano.jespresso.startup_services;

import org.jboss.logging.Logger;

import io.github.emanuelepaiano.jespresso.startup.StartupService;


/**
 * The Class GenericStartup.
 */
public class GenericStartup implements StartupService {
	
	/** The logger. */
	private Logger logger = Logger.getLogger(GenericStartup.class);

	/**
	 * Inits the.
	 */
	@Override
	public void init() {
		logger.info("[GenericStartup]: booting raw services");
	}
}
