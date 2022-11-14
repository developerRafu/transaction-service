package com.rafu.creditservice.repositories;

import com.rafu.creditservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
