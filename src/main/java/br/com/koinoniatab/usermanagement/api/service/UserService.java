package br.com.koinoniatab.usermanagement.api.service;

import br.com.koinoniatab.usermanagement.api.dto.UserCreateDTO;
import br.com.koinoniatab.usermanagement.api.dto.UserResponseDTO;
import br.com.koinoniatab.usermanagement.internal.dto.UserAuthDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO userEntity);
    Optional<UserResponseDTO> findUserByEmail(String email);
    Optional<UserResponseDTO> findUserById(Long id);
    Page<UserResponseDTO> findAllUsers(Pageable pageable);
    UserResponseDTO updateUser(Long id, UserCreateDTO userEntity);
    void deleteUser(Long id);
    Optional<UserAuthDTO> findByUsername(String username);
}
