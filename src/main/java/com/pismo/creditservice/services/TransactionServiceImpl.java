package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.Transaction;
import com.pismo.creditservice.errors.AccountNotFoundException;
import com.pismo.creditservice.errors.TransactionTypeNotFound;
import com.pismo.creditservice.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepository repository;
    private final IAccountService accountService;

    @Override
    public Transaction create(final Transaction transaction) {
        validateAccount(transaction);
        validateType(transaction);
        transaction.setEventDate(LocalDateTime.now());
        return repository.save(transaction);
    }

    private void validateType(final Transaction transaction) {
        if (transaction.getType() == null) {
            throw new TransactionTypeNotFound();
        }
    }

    private void validateAccount(final Transaction transaction) {
        final var accountId = transaction.getAccount().getId();
        final var account = accountService.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        transaction.setAccount(account);
    }
}
