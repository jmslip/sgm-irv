package br.com.koinoniatab.usermanagement.internal.mapper;

import br.com.koinoniatab.core.config.KoinoniaMapperConfig;
import br.com.koinoniatab.usermanagement.api.dto.UserCreateDTO;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(config = KoinoniaMapperConfig.class)
public interface UserMapper {

    @Mapping(target = "roleNames", source = "roles", qualifiedByName = "mapRoleNames")
    UserCreateDTO toDto(User user);

    @Mapping(target = "roles", ignore = true)
    User toEntity(UserCreateDTO userCreateDTO);

    default List<String> mapRoleNames(Set<Role> roles) {
        return roles.stream().map(Role::getName).toList();
    }
}
