package br.com.koinoniatab.usermanagement.api.service;

import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity createUser(UserEntity userEntity, List<Role> roleNames);
    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findUserById(Long id);
    Page<UserEntity> findAllUsers(Pageable pageable);
    UserEntity updateUser(Long id, UserEntity userEntity);
    void deleteUser(Long id);
}
