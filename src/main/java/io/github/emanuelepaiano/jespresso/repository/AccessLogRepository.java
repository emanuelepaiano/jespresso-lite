package io.github.emanuelepaiano.jespresso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.emanuelepaiano.jespresso.entity.AccessLog;

/**
 * The Interface AccessLogRepository.
 */
@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

}
