package io.github.emanuelepaiano.jespresso.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.emanuelepaiano.jespresso.dto.item.BrowserCount;
import io.github.emanuelepaiano.jespresso.dto.item.ItemList;
import io.github.emanuelepaiano.jespresso.dto.item.OsCount;
import io.github.emanuelepaiano.jespresso.service.InfoService;
import io.github.emanuelepaiano.jespresso.service.SessionService;
import io.github.emanuelepaiano.jespresso.util.DateUtils;

/**
 * The Class InfoServiceImpl.
 */
@Service
public class InfoServiceImpl implements InfoService {
	
	/** The session service. */
	@Autowired
	private SessionService sessionService;
	
	
	/**
	 * Gets the browsers count.
	 *
	 * @return the browsers count
	 */
	@Override
	public List<BrowserCount> getBrowsersCount() {
		return this.sessionService.getBrowsersCount();
	}

	/**
	 * Gets the available browsers.
	 *
	 * @return the available browsers
	 */
	@Override
	public List<String> getAvailableBrowsers() {
		return sessionService.getAvailableBrowsers();
	}

	/**
	 * Gets the available OS.
	 *
	 * @return the available OS
	 */
	@Override
	public List<String> getAvailableOS() {
		return this.sessionService.getAvailableOS();
	}
	
	/**
	 * Gets the browsers count.
	 *
	 * @return the browsers count
	 */
	@Override
	public List<OsCount> getOsCount() {
		return this.sessionService.getOsCount();
	}

	@Override
	public ItemList getLastSessions(Boolean paging, Integer pageSize, Integer page, Integer lastDays) {
		Date startDate = DateUtils.sumDays(new Date(), (-1)*lastDays);
		Date endDate = new Date();
		return sessionService.getSessionsBetween(new Timestamp(startDate.getTime()), 
				new Timestamp(endDate.getTime()), paging, page, pageSize);
	}

}
