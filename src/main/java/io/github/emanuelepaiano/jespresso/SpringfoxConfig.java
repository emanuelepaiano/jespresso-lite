package io.github.emanuelepaiano.jespresso;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SpringfoxConfig.
 */
@ConditionalOnProperty(prefix = "jespresso.api.springfox", name = "enable", matchIfMissing = false)
@Configuration
@EnableSwagger2
public class SpringfoxConfig {
	
	/**
	 * Api docket.
	 *
	 * @return the docket
	 */
	@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
