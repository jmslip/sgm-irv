package br.com.koinoniatab.usermanagement.internal.service;

import br.com.koinoniatab.core.exceptions.RoleNotFoundException;
import br.com.koinoniatab.core.exceptions.UserAlreadyExistsException;
import br.com.koinoniatab.core.exceptions.UserNotFoundException;
import br.com.koinoniatab.usermanagement.api.dto.UserCreateDTO;
import br.com.koinoniatab.usermanagement.api.dto.UserResponseDTO;
import br.com.koinoniatab.usermanagement.api.service.UserService;
import br.com.koinoniatab.usermanagement.internal.dto.UserAuthDTO;
import br.com.koinoniatab.usermanagement.internal.mapper.UserMapper;
import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.User;
import br.com.koinoniatab.usermanagement.internal.repository.RoleRepository;
import br.com.koinoniatab.usermanagement.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDTO createUser(UserCreateDTO newUser, List<Role> roleNames) {
        validateUserToCreate(newUser);

        User savedUser = registerNewUser(newUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> findUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toDto);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserCreateDTO userEntity) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado com o ID: " + id));

        userMapper.merge(user, userEntity);

        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserAuthDTO> findByUsername(String username) {
        return userRepository.findByEmail(username).map(userMapper::toAuthDTO);
    }

    private void validateUserToCreate(UserCreateDTO newUser) {
        if (userRepository.existsByEmail(newUser.email())) {
            throw new UserAlreadyExistsException("Erro ao cadastrar usuário com o e-mail " + newUser.email());
        }
    }

    private User registerNewUser(UserCreateDTO newUser) {
        User entity = userMapper.toEntity(newUser);
        setRoles(newUser.roleNames(), entity);
        entity.setPassword(passwordEncoder.encode(newUser.password()));
        return userRepository.save(entity);
    }

    private void setRoles(List<String> roleNames, User user) {
        Set<Role> assignedRoles = roleNames.stream()
                .map(roleName -> roleRepository.findByName(roleName)
                        .orElseThrow(() -> new RoleNotFoundException("Erro: Papel '" + roleName + "' não encontrado.")))
                .collect(Collectors.toSet());

        user.setRoles(assignedRoles);
    }
}
