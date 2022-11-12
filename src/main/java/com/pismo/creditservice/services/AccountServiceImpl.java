package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.Account;
import com.pismo.creditservice.errors.InvalidDocumentNumberException;
import com.pismo.creditservice.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository repository;

    @Override
    public Account create(final String documentNumber) {
        validateDocumentNumber(documentNumber);
        final var account = Account.builder().documentNumber(documentNumber).build();
        return repository.save(account);
    }

    @Override
    public Optional<Account> findById(final Long accountId) {
        return repository.findById(accountId);
    }

    private void validateDocumentNumber(final String documentNumber) {
        if (Objects.isNull(documentNumber) || documentNumber.length() < 11) {
            throw new InvalidDocumentNumberException(documentNumber);
        }
    }
}
