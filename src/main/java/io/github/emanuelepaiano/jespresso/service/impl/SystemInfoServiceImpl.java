package io.github.emanuelepaiano.jespresso.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.dto.item.SystemInfoDTO;
import io.github.emanuelepaiano.jespresso.dto.item.SystemMemoryDTO;
import io.github.emanuelepaiano.jespresso.service.SystemInfoService;
import io.github.emanuelepaiano.jespresso.util.SystemInfoHelper;

/**
 * The Class SystemInfoServiceImpl.
 */
@Service
public class SystemInfoServiceImpl implements SystemInfoService {

	/**
	 * Gets the system memory.
	 *
	 * @return the system memory
	 * @throws IOException
	 */
	public SystemMemoryDTO getSystemMemory() throws IOException {

		SystemMemoryDTO payload = new SystemMemoryDTO(SystemInfoHelper.getTotalMemory(),
				SystemInfoHelper.getFreeMemory(), SystemInfoHelper.getUsedMemory(), SystemInfoHelper.getMaxMemory(),
				SystemInfoHelper.getFreeSpace(), SystemInfoHelper.getTotalSpace());
		return payload;
	}

	/**
	 * Gets the system info.
	 *
	 * @return the system info
	 */
	public SystemInfoDTO getSystemInfo() {
		SystemInfoDTO payload = new SystemInfoDTO(SystemInfoHelper.getHostname(),
				SystemInfoHelper.getIPAddress(), SystemInfoHelper.getOSName(), SystemInfoHelper.getOSversion(),
				SystemInfoHelper.getJavaVersion(), SystemInfoHelper.getJavaVersion(),
				SystemInfoHelper.getOSArchVersion());
		return payload;
	}

}
