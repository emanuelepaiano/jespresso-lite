package io.github.emanuelepaiano.jespresso;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.github.emanuelepaiano.jespresso.startup.StartupService;
import io.github.emanuelepaiano.jespresso.startup_services.DefaultTimeZone;
import io.github.emanuelepaiano.jespresso.startup_services.GenericStartup;


/**
 * The Class Init.
 */
@Component
public class Init implements StartupService {
	
	
	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(Init.class);
	
	
	/** The service map. */
	private Map<String, StartupService> serviceMap;
		
	
	/**
	 * Instantiates a new inits the.
	 */
	public Init() {
		this.serviceMap = new HashMap<String, StartupService>();
		
		// add services here
		this.serviceMap.put("generic", new GenericStartup());
		this.serviceMap.put("default timezone", new DefaultTimeZone());
	}

	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		logger.info("[Init]: running startup services..");
		this.serviceMap.keySet().stream().forEach(key -> {
			logger.info("[INIT]: booting " + key + " service");
			this.serviceMap.get(key).init();
		});
	}

	
	/**
	 * Gets the service map.
	 *
	 * @return the service map
	 */
	public Map<String, StartupService> getServiceMap() {
		return serviceMap;
	}
	
}
