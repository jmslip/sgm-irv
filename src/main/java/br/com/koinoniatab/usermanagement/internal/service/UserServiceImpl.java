package br.com.koinoniatab.usermanagement.internal.service;

import br.com.koinoniatab.usermanagement.api.service.UserService;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public User createUser(User user, List<Role> roleNames) {
        return null;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        //TODO do implement delete user
    }
}
