package br.com.koinoniatab.usermanagement.internal.repository;

import br.com.koinoniatab.usermanagement.internal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
