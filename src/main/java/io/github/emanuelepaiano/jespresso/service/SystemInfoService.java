package io.github.emanuelepaiano.jespresso.service;

import java.io.IOException;

import io.github.emanuelepaiano.jespresso.dto.item.SystemInfoDTO;
import io.github.emanuelepaiano.jespresso.dto.item.SystemMemoryDTO;

/**
 * The Interface SystemInfoService.
 */
public interface SystemInfoService {
	
	/**
	 * Gets the system memory.
	 *
	 * @return the system memory
	 * @throws IOException 
	 */
	public SystemMemoryDTO getSystemMemory() throws IOException;
	
	/**
	 * Gets the system info.
	 *
	 * @return the system info
	 */
	public SystemInfoDTO getSystemInfo();

}
