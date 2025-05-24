package br.com.koinoniatab.usermanagement.internal.service;

import br.com.koinoniatab.usermanagement.api.service.UserService;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public UserEntity createUser(UserEntity userEntity, List<Role> roleNames) {
        return null;
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<UserEntity> findAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity userEntity) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        //TODO do implement delete user
    }
}
