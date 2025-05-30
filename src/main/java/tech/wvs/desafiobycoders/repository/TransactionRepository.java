package tech.wvs.desafiobycoders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.wvs.desafiobycoders.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
