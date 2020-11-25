package io.github.emanuelepaiano.jespresso.dto.request;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The Class SessionIntervalRequestDTO.
 */
public class SessionIntervalRequestDTO extends PageRequestDTO {
	
	/** The start date. */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp startDate;
	
	/** The end date. */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp endDate;

	/**
	 * Instantiates a new session interval request DTO.
	 *
	 * @param pageNumber the page number
	 * @param pageLimit the page limit
	 * @param startDate the start date
	 * @param endDate the end date
	 */
	public SessionIntervalRequestDTO(Integer pageNumber, Integer pageLimit, Timestamp startDate, Timestamp endDate) {
		super(pageNumber, pageLimit);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Timestamp getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Timestamp getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SessionIntervalRequestDTO [startDate=" + startDate + ", endDate=" + endDate + ", pageNumber="
				+ getPageNumber() + ", pageLimit=" + getPageLimit() + "]";
	}
	
	

}
