package io.github.emanuelepaiano.jespresso.util;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class UserAgentUtils.
 */
public class UserAgentUtils {
	
	/**
	 * Gets the browser.
	 *
	 * @param request the request
	 * @return the browser
	 */
	public static String getBrowser(HttpServletRequest request) {
		if (isBrowserFirefox(request)) 
			return "Firefox";
		
		if (isBrowserChrome(request)) 
			return "Chrome";
		
		if (isBrowserIE(request)) 
			return "Internet Explorer";
		
		if (isBrowserOpera(request)) 
			return "Opera";
		
		if (isBrowserAndroid(request)) 
			return "Embedded Android";
		
		if (isBrowserSafari(request)) 
			return "Safari";
		
		if (isPostman(request)) 
			return "Postman client";
		
		return "Unknown";
	}
	
	/**
	 * Gets the operating system.
	 *
	 * @param request the request
	 * @return the operating system
	 */
	public static  String getOperatingSystem(HttpServletRequest request) {
		if (isOSLinux(request)) 
			return "Linux";
		
		if (isOSAndroid(request)) 
			return "Android";
		
		if (isOSWindows(request)) 
			return "Windows";
		
		if (isOSBlackBerry(request)) 
			return "BlackBerry";
		
		if (isOSIPhone(request)) 
			return "iOS on Iphone";
		
		if (isOSIPad(request)) 
			return "iOS on Ipad";
		
		if (isOSMAC(request)) 
			return "Macintosh / Mac OS X";
		
		if (isOSIOS(request)) 
			return "iOS";
		
		return "Unknown";
	}
	
	/**
	 * Gets the agent.
	 *
	 * @param request the request
	 * @return the agent
	 */
	public static String getAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
	
	// detecting browsers
	
	/**
	 * Checks if is browser firefox.
	 *
	 * @param request the request
	 * @return true, if is browser firefox
	 */
	public static  boolean isBrowserFirefox(HttpServletRequest request) {
		return getAgent(request).contains("Firefox");
	}
	
	/**
	 * Checks if is browser chrome.
	 *
	 * @param request the request
	 * @return true, if is browser chrome
	 */
	public static  boolean isBrowserChrome(HttpServletRequest request) {
		return getAgent(request).contains("Chrome");
	}

	
	/**
	 * Checks if is postman.
	 *
	 * @param request the request
	 * @return true, if is postman
	 */
	public static  boolean isPostman(HttpServletRequest request) {
		return getAgent(request).contains("Postman");
	}
	
	/**
	 * Checks if is browser IE.
	 *
	 * @param request the request
	 * @return true, if is browser IE
	 */
	public static boolean isBrowserIE(HttpServletRequest request) {
		return getAgent(request).contains("MSIE");
	}
	
	/**
	 * Checks if is browser opera.
	 *
	 * @param request the request
	 * @return true, if is browser opera
	 */
	public static boolean isBrowserOpera(HttpServletRequest request) {
		return getAgent(request).contains("Opera");
	}
	
	/**
	 * Checks if is browser safari.
	 *
	 * @param request the request
	 * @return true, if is browser safari
	 */
	public static boolean isBrowserSafari(HttpServletRequest request) {
		return isOSIOS(request) && getAgent(request).contains("Safari");
	}
	
	/**
	 * Checks if is browser android.
	 *
	 * @param request the request
	 * @return true, if is browser android
	 */
	public static boolean isBrowserAndroid(HttpServletRequest request) {
		return isOSLinux(request) && getAgent(request).contains("Android");
	}
	
	// detecting Operating System
	
	/**
	 * Checks if is OS windows.
	 *
	 * @param request the request
	 * @return true, if is OS windows
	 */
	public static boolean isOSWindows(HttpServletRequest request) {
		return getAgent(request).contains("Windows");
	}
	
	/**
	 * Checks if is OS linux.
	 *
	 * @param request the request
	 * @return true, if is OS linux
	 */
	public static boolean isOSLinux(HttpServletRequest request) {
		return getAgent(request).contains("Linux");
	}

	/**
	 * Checks if is OS android.
	 *
	 * @param request the request
	 * @return true, if is OS android
	 */
	public static boolean isOSAndroid(HttpServletRequest request) {
		return getAgent(request).contains("Android");
	}
	
	/**
	 * Checks if is osmac.
	 *
	 * @param request the request
	 * @return true, if is osmac
	 */
	public static boolean isOSMAC(HttpServletRequest request) {
		return getAgent(request).contains("Mac");
	}
	
	/**
	 * Checks if is OS black berry.
	 *
	 * @param request the request
	 * @return true, if is OS black berry
	 */
	public static boolean isOSBlackBerry(HttpServletRequest request) {
		return getAgent(request).contains("BlackBerry");
	}
	
	/**
	 * Checks if is os ios.
	 *
	 * @param request the request
	 * @return true, if is os ios
	 */
	public static boolean isOSIOS(HttpServletRequest request) {
		return isOSIPad(request) || isOSIPhone(request);
	}
	
	/**
	 * Checks if is OSI pad.
	 *
	 * @param request the request
	 * @return true, if is OSI pad
	 */
	public static boolean isOSIPad(HttpServletRequest request) {
		return isOSMAC(request) && getAgent(request).contains("iPad");
	}
	
	/**
	 * Checks if is OSI phone.
	 *
	 * @param request the request
	 * @return true, if is OSI phone
	 */
	public static boolean isOSIPhone(HttpServletRequest request) {
		return isOSMAC(request) && getAgent(request).contains("iPhone");
	}
}
