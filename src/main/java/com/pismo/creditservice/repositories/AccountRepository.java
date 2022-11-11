package com.pismo.creditservice.repositories;

import com.pismo.creditservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
