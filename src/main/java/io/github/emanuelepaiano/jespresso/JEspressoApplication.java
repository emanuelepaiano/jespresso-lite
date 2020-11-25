package io.github.emanuelepaiano.jespresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class JEspressoApplication.
 */
@EnableAutoConfiguration
@SpringBootApplication
@Configuration
public class JEspressoApplication implements WebMvcConfigurer{
	
	/**
	 * Rest template.
	 *
	 * @return the rest template
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(JEspressoApplication.class, args);
	}
	
	/**
	 * Adds the cors mappings.
	 *
	 * @param registry the registry
	 */
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedHeaders("*").allowedOrigins("*")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS").allowCredentials(true);
	}

}
