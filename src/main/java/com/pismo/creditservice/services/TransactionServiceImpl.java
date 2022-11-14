package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.OperationType;
import com.pismo.creditservice.domain.Transaction;
import com.pismo.creditservice.errors.AccountNotFoundException;
import com.pismo.creditservice.errors.InvalidBankOperation;
import com.pismo.creditservice.errors.TransactionTypeNotFound;
import com.pismo.creditservice.repositories.TransactionRepository;
import com.pismo.creditservice.utils.models.BankStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepository repository;
    private final IAccountService accountService;
    private final IOperationTypeService operationTypeService;

    @Override
    @Transactional
    public Transaction create(final Transaction transaction) {
        validateAccount(transaction);
        validateType(transaction);
        transaction.setEventDate(LocalDateTime.now());
        return repository.save(transaction);
    }

    private void validateType(final Transaction transaction) {
        if (transaction.getOperationType() == null) {
            throw new TransactionTypeNotFound(null);
        }

        final var operation = operationTypeService
                .findById(transaction.getOperationType().getId())
                .orElseThrow(() -> new TransactionTypeNotFound(transaction.getOperationType().getId()));

        final var bankStatement = isValidBankStatement(transaction, operation);
        if (!bankStatement.isValid()) {
            throw new InvalidBankOperation(operation.getDescription());
        }
        transaction.setOperationType(operation);
    }

    private void validateAccount(final Transaction transaction) {
        final var accountId = transaction.getAccount().getId();
        final var account = accountService.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
        transaction.setAccount(account);
    }

    private BankStatement isValidBankStatement(final Transaction transaction, final OperationType operation) {
        return BankStatement
                .builder()
                .amount(transaction.getAmount())
                .operationTypeEnum(operation.getDescription())
                .build();
    }
}
