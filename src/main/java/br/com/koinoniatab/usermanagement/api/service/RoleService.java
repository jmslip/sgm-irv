package br.com.koinoniatab.usermanagement.api.service;

import br.com.koinoniatab.usermanagement.api.dto.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> findAllRoles();
}
