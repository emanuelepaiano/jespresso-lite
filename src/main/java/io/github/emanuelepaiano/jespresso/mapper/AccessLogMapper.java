package io.github.emanuelepaiano.jespresso.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.github.emanuelepaiano.jespresso.dto.item.AccessLogDTO;
import io.github.emanuelepaiano.jespresso.entity.AccessLog;
import io.github.emanuelepaiano.jespresso.entity.Session;


/**
 * The Interface AccessLogMapper.
 */
@Mapper
public interface AccessLogMapper {
	/** The instance. */
	AccessLogMapper INSTANCE = Mappers.getMapper(AccessLogMapper.class);
	
	
	/**
	 * Access log to access log DTO.
	 *
	 * @param accessLog the access log
	 * @return the access log DTO
	 */
	AccessLogDTO accessLogToAccessLogDTO(AccessLog accessLog);
	
	
	/**
	 * Access log DTO to access log.
	 *
	 * @param accessLogDTO the access log DTO
	 * @return the access log
	 */
	AccessLog accessLogDTOToAccessLog(AccessLogDTO accessLogDTO);
	
	
	/**
	 * Session to access log.
	 *
	 * @param accessLog the access log
	 * @return the access log
	 */
	AccessLog sessionToAccessLog(Session session);
}
