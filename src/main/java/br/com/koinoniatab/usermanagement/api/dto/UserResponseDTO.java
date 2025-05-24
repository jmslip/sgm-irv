package br.com.koinoniatab.usermanagement.api.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record UserResponseDTO(
        Long id,
        String name,
        String email,
        LocalDate birthDate,
        boolean active,
        List<String> roleNames
) {
}
