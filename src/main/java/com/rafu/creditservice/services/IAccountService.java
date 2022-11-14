package com.rafu.creditservice.services;

import com.rafu.creditservice.domain.Account;

import java.util.Optional;

public interface IAccountService {
    Account create(final String documentNumber);

    Optional<Account> findById(Long accountId);
}
