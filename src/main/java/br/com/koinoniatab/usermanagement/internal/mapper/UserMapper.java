package br.com.koinoniatab.usermanagement.internal.mapper;

import br.com.koinoniatab.core.config.KoinoniaMapperConfig;
import br.com.koinoniatab.usermanagement.api.dto.UserCreateDTO;
import br.com.koinoniatab.usermanagement.api.dto.UserResponseDTO;
import br.com.koinoniatab.usermanagement.internal.dto.UserAuthDTO;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(config = KoinoniaMapperConfig.class)
public interface UserMapper {

    @Mapping(target = "roleNames", source = "roles", qualifiedByName = "mapRoleNames")
    UserResponseDTO toDto(User user);

    @Mapping(target = "roleNames", source = "roles", qualifiedByName = "mapRoleNames")
    UserAuthDTO toAuthDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "active", source = "active", qualifiedByName = "mapActive")
    @Mapping(target = "password", ignore = true)
    User toEntity(UserCreateDTO userCreateDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", source = "active", qualifiedByName = "mapActive")
    void merge(@MappingTarget User userToUpdate, UserCreateDTO userCreateDTO);

    @Named("mapRoleNames")
    default List<String> mapRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).toList();
    }

    @Named("mapActive")
    default boolean mapActive(Boolean active) {
        if (active == null) {
            return true;
        }
        return active;
    }
}
