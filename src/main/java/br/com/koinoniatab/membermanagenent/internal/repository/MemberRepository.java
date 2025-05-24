package br.com.koinoniatab.membermanagenent.internal.repository;

import br.com.koinoniatab.membermanagenent.internal.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
