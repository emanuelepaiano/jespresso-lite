package io.github.emanuelepaiano.jespresso.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.github.emanuelepaiano.jespresso.dto.item.AdministratorDTO;
import io.github.emanuelepaiano.jespresso.entity.AdminUser;

/**
 * The Interface AdministratorMapper.
 */
@Mapper
public interface AdministratorMapper {
	
	/** The instance. */
	public static AdministratorMapper INSTANCE = Mappers.getMapper(AdministratorMapper.class);
	
	/**
	 * To administrator.
	 *
	 * @param administratorOutDTO the administrator out DTO
	 * @return the administrator
	 */
	public AdminUser toAdministrator(AdministratorDTO administratorOutDTO);
	
	/**
	 * To administrator out DTO.
	 *
	 * @param administrator the administrator
	 * @return the administrator out DTO
	 */
	public AdministratorDTO toAdministratorOutDTO(AdminUser administrator);

}
