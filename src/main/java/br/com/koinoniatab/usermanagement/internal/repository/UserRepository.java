package br.com.koinoniatab.usermanagement.internal.repository;

import br.com.koinoniatab.usermanagement.internal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
