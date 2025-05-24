package br.com.koinoniatab.usermanagement.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Valid
@Builder
public record UserCreateDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres.")
        String name,
        @NotBlank(message = "O e-mail é obrigatório.")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
        String email,
        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, max = 100, message = "A senha deve ter entre 8 e 100 caracteres.")
        String password,
        @NotNull(message = "A data de nascimento é obrigatória.")
        @Past(message = "A data de nascimento deve ser no passado.")
        LocalDate birthDate,
        Boolean active,
        @NotEmpty(message = "Pelo menos um papel deve ser selecionado.")
        List<String> roleNames
) {
}
