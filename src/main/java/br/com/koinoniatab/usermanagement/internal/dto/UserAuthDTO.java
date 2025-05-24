package br.com.koinoniatab.usermanagement.internal.dto;

import java.time.LocalDate;
import java.util.List;

public record UserAuthDTO(
        Long id,
        String name,
        String email,
        String password,
        LocalDate birthDate,
        boolean active,
        List<String> roleNames
) {
}
