package br.com.koinoniatab.usermanagement.internal.service;

import br.com.koinoniatab.usermanagement.api.dto.RoleResponseDTO;
import br.com.koinoniatab.usermanagement.api.service.RoleService;
import br.com.koinoniatab.usermanagement.internal.mapper.RoleMapper;
import br.com.koinoniatab.usermanagement.internal.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponseDTO> findAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::toDto).toList();
    }
}
