package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.Account;
import com.pismo.creditservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository repository;

    @Override
    public Account create(final String documentNumber) {
        final var account = Account.builder().documentNumber(documentNumber).build();
        return repository.save(account);
    }
}
