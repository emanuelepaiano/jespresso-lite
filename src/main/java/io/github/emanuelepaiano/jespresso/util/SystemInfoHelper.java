package io.github.emanuelepaiano.jespresso.util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The Class SystemInfoHelper.
 */
public class SystemInfoHelper {
	
	
	/**
	 * Gets the max memory.
	 *
	 * @return the max memory
	 */
	public static long getMaxMemory() {
	    return Runtime.getRuntime().maxMemory();
	}

	/**
	 * Gets the used memory.
	 *
	 * @return the used memory
	 */
	public static long getUsedMemory() {
	    return getMaxMemory() - getFreeMemory();
	}

	/**
	 * Gets the total memory.
	 *
	 * @return the total memory
	 */
	public static long getTotalMemory() {
	    return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Gets the free memory.
	 *
	 * @return the free memory
	 */
	public static long getFreeMemory() {
	    return Runtime.getRuntime().freeMemory();
	}
	
	/**
	 * Gets the OS name.
	 *
	 * @return the OS name
	 */
	public static String getOSName() {
		return System.getProperty("os.name");
	}
	
	/**
	 * Gets the o sversion.
	 *
	 * @return the o sversion
	 */
	public static String getOSversion() {
		return System.getProperty("os.version");
	}
	
	/**
	 * Gets the java vendor.
	 *
	 * @return the java vendor
	 */
	public static String getJavaVendor() {
		return System.getProperty("java.vendor");
	}
	
	/**
	 * Gets the java version.
	 *
	 * @return the java version
	 */
	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}
	
	/**
	 * Gets the OS arch version.
	 *
	 * @return the OS arch version
	 */
	public static String getOSArchVersion() {
		return System.getProperty("os.arch");
	}
	
	/**
	 * Gets the hostname.
	 *
	 * @return the hostname
	 */
	public static String getHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "Unknown";
		}
	}
	
	/**
	 * Gets the IP address.
	 *
	 * @return the IP address
	 */
	public static String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "Unknown";
		}
	}
	
	/**
	 * Gets the free space.
	 *
	 * @return the free space
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Long getFreeSpace() throws IOException {
		String path = System.getProperty("user.dir");
		File file = new File(path);
		return file.getFreeSpace();
	}
	
	/**
	 * Gets the total space.
	 *
	 * @return the total space
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Long getTotalSpace() throws IOException {
		String path = System.getProperty("user.dir");
		File file = new File(path);
		return file.getTotalSpace();
	}
}
