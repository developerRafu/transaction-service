package com.rafu.creditservice.repositories;

import com.rafu.creditservice.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.id = :accountId " +
            "AND t.paid = FALSE " +
            "AND t.amount < 0")
    List<Transaction> findAllNotPaidByAccountId(@Param("accountId") Long accountId);
}
