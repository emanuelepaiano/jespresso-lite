package io.github.emanuelepaiano.jespresso.dto.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Getter
@ToString
@NoArgsConstructor
public class ConfigVarsFeDTO {
	
	/** The landing url. */
	@JsonProperty("landing_url")
	@Value("${jespresso.landing.path}")
	private String landingUrl;
	
	/** The admin url. */
	@JsonProperty("admin_url")
	@Value("${jespresso.admin.path}")
	private String adminUrl;
	
	/** The front end base url. */
	@JsonProperty("frontend_base_url")
	@Value("${jespresso.frontend.base.url}")
	private String frontEndBaseUrl;
	
	@JsonProperty("unifi_site_name")
	@Value("${unifiApi.controller.sitename}")
	private String unifiSiteName;
	
	/**
	 * Gets the landing url.
	 *
	 * @return the landing url
	 */
	@JsonProperty("landing_base_url")
	private String getLandingUrl() {
		return this.frontEndBaseUrl + landingUrl;
	}
	
	/**
	 * Gets the admin url.
	 *
	 * @return the admin url
	 */
	@JsonProperty("admin_base_url")
	private String getAdminUrl() {
		return this.frontEndBaseUrl + adminUrl;
	}
	
}
