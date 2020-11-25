package io.github.emanuelepaiano.jespresso.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.emanuelepaiano.jespresso.entity.Session;

/**
 * The Interface SessionRepository.
 */
@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
	
	/**
	 * Find by device mac.
	 *
	 * @param deviceMac the device mac
	 * @return the optional
	 */
	Optional<Session> findByDeviceMac(@Param("deviceMac") String deviceMac);
	
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<Session> findAll(Pageable pageable);
	
	
	/**
	 * Find all browsers.
	 *
	 * @return the list
	 */
	@Query("select DISTINCT(s.browser) from Session s")
	List<String> findAllBrowsers();
	
	
	/**
	 * Find all os.
	 *
	 * @return the list
	 */
	@Query("select DISTINCT(s.operatingSystem) from Session s")
	List<String> findAllOs();
	
	/**
	 * Gets the expired sessions.
	 *
	 * @param current the current
	 * @return the expired sessions
	 */
	List<Session> findAllByExpireLoginOnLessThanEqual(Date current);
	
	/**
	 * Gets the valid sessions.
	 *
	 * @param current the current
	 * @return the valid sessions
	 */
	List<Session> findAllByExpireLoginOnGreaterThan(Date current);
	
	/**
	 * Gets the browsers count.
	 *
	 * @return the browsers count
	 */
	@Query("select s.browser as browser, count(s.browser) as quantity from Session s group by s.browser")
	List<Object[]> getBrowsersCount();
	
	/**
	 * Gets the browsers count.
	 *
	 * @return the browsers count
	 */
	@Query("select s.operatingSystem as os, count(s.operatingSystem) as quantity from Session s group by s.operatingSystem")
	List<Object[]> getOsCount();
	
	
	/**
	 * Find all by last login on between.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param pageable the pageable
	 * @return the list
	 */
	List<Session> findAllByLastLoginOnBetweenOrderByLastLoginOnDesc(Timestamp startDate, Timestamp endDate, Pageable pageable);
	
	
	/**
	 * Find all by last login on between.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list
	 */
	List<Session> findAllByLastLoginOnBetweenOrderByLastLoginOnDesc(Timestamp startDate, Timestamp endDate);
	
	
	
	/**
	 * Delete all by remove session on less than equal.
	 *
	 * @param removeSessionOn the remove session on
	 * @return the list
	 */
	List<Session> deleteAllByRemoveSessionOnLessThanEqual(Timestamp removeSessionOn);

}
