package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.Account;

import java.util.Optional;

public interface IAccountService {
    Account create(final String documentNumber);

    Optional<Account> findById(Long accountId);
}
