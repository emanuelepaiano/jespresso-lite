package io.github.emanuelepaiano.jespresso.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.github.emanuelepaiano.jespresso.dto.item.SessionDTO;
import io.github.emanuelepaiano.jespresso.entity.Session;

/**
 * The Interface SessionMapper.
 */
@Mapper
public interface SessionMapper {

	/** The instance. */
	SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);
	
	/**
	 * Session to session DTO.
	 *
	 * @param session the session
	 * @return the session DTO
	 */
	SessionDTO SessionToSessionDTO(Session session);
	
	/**
	 * Session DTO to session.
	 *
	 * @param sessionDTO the session DTO
	 * @return the session
	 */
	Session SessionDTOToSession(SessionDTO sessionDTO);
	
}
