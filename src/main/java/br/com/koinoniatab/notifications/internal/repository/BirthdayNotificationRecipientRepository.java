package br.com.koinoniatab.notifications.internal.repository;

import br.com.koinoniatab.notifications.internal.model.BirthdayNotificationRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthdayNotificationRecipientRepository extends JpaRepository<BirthdayNotificationRecipient, Long> {
}
