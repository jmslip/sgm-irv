package br.com.koinoniatab.usermanagement.api.service;

import br.com.koinoniatab.usermanagement.api.dto.UserCreateDTO;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserCreateDTO createUser(UserCreateDTO userEntity, List<Role> roleNames);
    Optional<UserCreateDTO> findUserByEmail(String email);
    Optional<UserCreateDTO> findUserById(Long id);
    Page<UserCreateDTO> findAllUsers(Pageable pageable);
    UserCreateDTO updateUser(Long id, UserCreateDTO userEntity);
    void deleteUser(Long id);
}
