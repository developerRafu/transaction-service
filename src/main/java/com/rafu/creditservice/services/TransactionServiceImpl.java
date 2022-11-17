package com.rafu.creditservice.services;

import com.rafu.creditservice.domain.OperationType;
import com.rafu.creditservice.domain.Transaction;
import com.rafu.creditservice.errors.AccountNotFoundException;
import com.rafu.creditservice.errors.InvalidBankOperation;
import com.rafu.creditservice.errors.OverpaymentException;
import com.rafu.creditservice.errors.TransactionTypeNotFound;
import com.rafu.creditservice.repositories.TransactionRepository;
import com.rafu.creditservice.utils.models.BankStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        transaction.setBalance(transaction.getAmount());
        if (bankStatement.isAmountOut()) {
            return;
        }

        final var transactionsNotPaid = this.repository.findAllNotPaidByAccountId(transaction.getAccount().getId());
        transactionsNotPaid.forEach(t -> pay(transaction, t));
        if (!isEqualToZero(transaction.getBalance())) {
            throw new OverpaymentException();
        }
    }

    private void pay(final Transaction paymentTransaction, final Transaction transaction) {
        setPaymentBalance(paymentTransaction, transaction);
        transaction.setPaid(isEqualToZero(transaction.getBalance()));
    }

    private void setPaymentBalance(Transaction paymentTransaction, Transaction transaction) {
        if (paymentTransaction.getBalance().compareTo(getAmountPositive(transaction)) > 0) {
            transaction.setBalance(BigDecimal.ZERO);
            final var paymentAmount = paymentTransaction.getBalance();
            paymentTransaction.setBalance(paymentAmount.add(transaction.getAmount()));
            return;
        }
        final var balance = transaction.getAmount().add(paymentTransaction.getBalance());
        transaction.setBalance(balance);
        paymentTransaction.setBalance(BigDecimal.ZERO);
    }

    private static BigDecimal getAmountPositive(Transaction transaction) {
        return transaction.getAmount().compareTo(BigDecimal.ZERO) < 0 ? transaction.getAmount().negate() : transaction.getAmount();
    }

    private static boolean isEqualToZero(final BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) == 0;
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
