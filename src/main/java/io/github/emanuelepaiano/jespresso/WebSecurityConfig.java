package io.github.emanuelepaiano.jespresso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.emanuelepaiano.jespresso.handler.ControllerInterceptorHandler;
import io.github.emanuelepaiano.jespresso.security.JwtAuthenticationEntryPoint;
import io.github.emanuelepaiano.jespresso.security.JwtRequestFilter;

/**
 * The Class WebSecurityConfig.
 */
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	/** The jwt authentication entry point. */
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	/** The jwt user details service. */
	@Autowired
	private UserDetailsService jwtUserDetailsService;

	/** The jwt request filter. */
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/** The landing path. */
	@Value("${jespresso.landing.path}")
	private String landingPath;

	/** The admin path. */
	@Value("${jespresso.admin.path}")
	private String adminPath;

	/**
	 * Configure global.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Authentication manager bean.
	 *
	 * @return the authentication manager
	 * @throws Exception the exception
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

	/**
	 * Adds the interceptors.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ControllerInterceptorHandler());
	}

	/**
	 * Adds the resource handlers.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(this.landingPath + "/**").addResourceLocations("classpath:/static/landing/");
		registry.addResourceHandler(this.adminPath + "/**").addResourceLocations("classpath:/static/admin/");
	}

	/**
	 * Adds the view controllers.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(this.landingPath + "/").setViewName("forward:" + this.landingPath + "/index.html");
		registry.addViewController(this.adminPath + "/").setViewName("forward:" + this.adminPath + "/index.html");
	}

	/**
	 * Configure.
	 *
	 * @param httpSecurity the http security
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// don't authenticate this particular request
				.authorizeRequests().antMatchers("/api/authenticate", landingPath + "/**", adminPath + "/**",
						"/resource**", "/api/authorize-guest", "/h2-console/*", "/v2/api-docs*", "/api/config/vars/**").permitAll()
				// all other requests need to be authenticated
				.anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
