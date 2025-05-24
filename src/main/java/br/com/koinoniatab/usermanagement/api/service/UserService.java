package br.com.koinoniatab.usermanagement.api.service;

import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user, List<Role> roleNames);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);
    Page<User> findAllUsers(Pageable pageable);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
