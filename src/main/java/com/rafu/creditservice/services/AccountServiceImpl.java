package com.rafu.creditservice.services;

import com.rafu.creditservice.domain.Account;
import com.rafu.creditservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository repository;

    @Override
    public Account create(final String documentNumber) {
        final var account = Account.builder().documentNumber(documentNumber).build();
        return repository.save(account);
    }

    @Override
    public Optional<Account> findById(final Long accountId) {
        return repository.findById(accountId);
    }

}
