package com.pismo.creditservice.repositories;

import com.pismo.creditservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
