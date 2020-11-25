package io.github.emanuelepaiano.jespresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.emanuelepaiano.jespresso.dto.item.ConfigVarsFeDTO;

/**
 * The Class ConfigVarsController.
 */
@RestController
@RequestMapping("/api/config/vars")
public class ConfigVarsController {
	
	@Autowired
	private ConfigVarsFeDTO configVars;
	
	/**
	 * Gets the config vars.
	 *
	 * @return the config vars
	 */
	@GetMapping("/frontend")
	public ResponseEntity<ConfigVarsFeDTO> getConfigVars() {
		return ResponseEntity.ok(configVars);
	}
	
}
