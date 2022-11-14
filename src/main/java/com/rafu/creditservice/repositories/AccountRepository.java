package com.rafu.creditservice.repositories;

import com.rafu.creditservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
