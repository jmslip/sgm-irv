package br.com.koinoniatab.usermanagement.internal.mapper;

import br.com.koinoniatab.core.config.KoinoniaMapperConfig;
import br.com.koinoniatab.usermanagement.api.dto.RoleResponseDTO;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import org.mapstruct.Mapper;

@Mapper(config = KoinoniaMapperConfig.class)
public interface RoleMapper {
    RoleResponseDTO toDto(Role role);
    Role toEntity(RoleResponseDTO roleResponseDTO);
}
