package io.github.emanuelepaiano.jespresso.service;

import java.util.List;

import io.github.emanuelepaiano.jespresso.dto.item.BrowserCount;
import io.github.emanuelepaiano.jespresso.dto.item.ItemList;
import io.github.emanuelepaiano.jespresso.dto.item.OsCount;


/**
 * The Interface InfoService.
 */
public interface InfoService {
	
	/**
	 * Gets the browsers.
	 *
	 * @return the browsers
	 */
	public List<BrowserCount> getBrowsersCount();
	
	
	/**
	 * Gets the available browsers.
	 *
	 * @return the available browsers
	 */
	public List<String> getAvailableBrowsers();
	
	
	/**
	 * Gets the available OS.
	 *
	 * @return the available OSes
	 */
	public List<String> getAvailableOS();

	
	/**
	 * Gets the os count.
	 *
	 * @return the os count
	 */
	public List<OsCount> getOsCount();
	
	
	/**
	 * Gets the last sessions.
	 *
	 * @return the last sessions
	 */
	public ItemList getLastSessions(Boolean paging, Integer pageSize, Integer page, Integer lastDays);

}
