package br.com.koinoniatab.usermanagement.internal.service;

import br.com.koinoniatab.usermanagement.api.dto.UserCreateDTO;
import br.com.koinoniatab.usermanagement.api.service.UserService;
import br.com.koinoniatab.usermanagement.internal.mapper.UserMapper;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserCreateDTO createUser(UserCreateDTO userEntity, List<Role> roleNames) {
        return null;
    }

    @Override
    public Optional<UserCreateDTO> findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDto);
    }

    @Override
    public Optional<UserCreateDTO> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Page<UserCreateDTO> findAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public UserCreateDTO updateUser(Long id, UserCreateDTO userEntity) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        //TODO do implement delete user
    }
}
