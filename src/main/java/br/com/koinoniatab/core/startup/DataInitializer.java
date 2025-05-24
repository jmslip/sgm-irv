package br.com.koinoniatab.core.startup;

import br.com.koinoniatab.usermanagement.internal.model.Role;
import br.com.koinoniatab.usermanagement.internal.model.User;
import br.com.koinoniatab.usermanagement.internal.repository.RoleRepository;
import br.com.koinoniatab.usermanagement.internal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Iniciando a criação de dados iniciais...");

        // Criar Papéis (Roles)
        Role roleAdmin = createRoleIfNotExists("ROLE_ADMIN");
        Role roleUser = createRoleIfNotExists("ROLE_USER");

        // Criar Usuário Administrador
        createUserIfNotExists(
                "Admin",
                "admin@suaigreja.com",
                "admin123",
                LocalDate.of(1990, 1, 1),
                new HashSet<>(Arrays.asList(roleAdmin, roleUser))
        );

        // Criar Usuário Comum (Exemplo)
        createUserIfNotExists(
                "Usuario Comum",
                "user@suaigreja.com",
                "user123",
                LocalDate.of(1995, 5, 10),
                new HashSet<>(List.of(roleUser))
        );

        log.info("Criação de dados iniciais concluída.");
    }

    private Role createRoleIfNotExists(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    log.info("Criando papel: {}", roleName);
                    return roleRepository.save(new Role(roleName));
                });
    }

    private void createUserIfNotExists(String name, String email, String rawPassword, LocalDate birthDate, Set<Role> roles) {
        if (!userRepository.existsByEmail(email)) {
            log.info("Criando usuário: {}", email);
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setBirthDate(birthDate);
            user.setRoles(roles);
            userRepository.save(user);
        } else {
            log.info("Usuário {} já existe.", email);
        }
    }
}
