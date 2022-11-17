package com.rafu.creditservice.services;

import com.rafu.creditservice.domain.enums.OperationTypeEnum;
import com.rafu.creditservice.errors.AccountNotFoundException;
import com.rafu.creditservice.errors.InvalidBankOperation;
import com.rafu.creditservice.errors.OverpaymentException;
import com.rafu.creditservice.errors.TransactionTypeNotFound;
import com.rafu.creditservice.helpers.AccountMockBuilder;
import com.rafu.creditservice.helpers.OperationTypeMockBuilder;
import com.rafu.creditservice.helpers.TransactionMockBuilder;
import com.rafu.creditservice.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {
    TransactionRepository repository;
    ITransactionService service;
    IAccountService accountService;
    IOperationTypeService operationTypeService;

    @BeforeEach
    void setUp() {
        repository = mock(TransactionRepository.class);
        accountService = mock(IAccountService.class);
        operationTypeService = mock(IOperationTypeService.class);
        service = new TransactionServiceImpl(repository, accountService, operationTypeService);
    }

    @Nested
    class createTests {
        @Test
        void shouldCreateATransaction() {
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            final var account = AccountMockBuilder.mockDefaultValues();
            final var operation = OperationTypeMockBuilder.mockDefaultValues();
            when(repository.save(any())).thenReturn(transaction);
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(operation));
            final var result = service.create(transaction);
            assertEquals(transaction, result);
        }

        @Test
        void shouldTrowsAnExceptionWhenNotFoundAccount() {
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            when(repository.save(any())).thenReturn(transaction);
            when(accountService.findById(any())).thenReturn(Optional.empty());
            assertThrows(AccountNotFoundException.class, () -> service.create(transaction));
        }

        @Test
        void shouldTrowsAnExceptionWhenTypeIsNull() {
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            transaction.setOperationType(null);
            final var account = AccountMockBuilder.mockDefaultValues();
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            assertThrows(TransactionTypeNotFound.class, () -> service.create(transaction));
        }

        @Test
        void shouldTrowsAnExceptionWhenTypeIsValidButAmountIsNot() {
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            transaction.setAmount(BigDecimal.valueOf(100));
            final var account = AccountMockBuilder.mockDefaultValues();
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(transaction.getOperationType()));
            assertThrows(InvalidBankOperation.class, () -> service.create(transaction));
        }

        @Test
        void shouldTrowsAnExceptionWhenTypeIsINSTALLMENT_PURCHASEAndAmountIsPositive() {
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            transaction.setAmount(BigDecimal.valueOf(100));
            transaction.getOperationType().setDescription(OperationTypeEnum.INSTALLMENT_PURCHASE);
            final var account = AccountMockBuilder.mockDefaultValues();
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(transaction.getOperationType()));
            assertThrows(InvalidBankOperation.class, () -> service.create(transaction));
        }

        @Test
        void shouldTrowsAnExceptionWhenTypeIsWITHDRAWALAndAmountIsPositive() {
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            transaction.setAmount(BigDecimal.valueOf(100));
            transaction.getOperationType().setDescription(OperationTypeEnum.WITHDRAWAL);
            final var account = AccountMockBuilder.mockDefaultValues();
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(transaction.getOperationType()));
            assertThrows(InvalidBankOperation.class, () -> service.create(transaction));
        }

        @Test
        void shouldTrowsAnExceptionWhenTypeIsPAYMENTAndAmountIsNegative() {
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            transaction.getOperationType().setDescription(OperationTypeEnum.PAYMENT);
            final var account = AccountMockBuilder.mockDefaultValues();
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(transaction.getOperationType()));
            assertThrows(InvalidBankOperation.class, () -> service.create(transaction));
        }

        @Test
        void shouldReturnAValidTransactionWhenISentA150AmountPaymentAndHaveA100AmountPurchase() {
            final var payment = TransactionMockBuilder.mockDefaultPayment();
            final var purchaseNotPaid = Collections.singletonList(TransactionMockBuilder.mockDefaultValues());
            final var operation = OperationTypeMockBuilder.mockDefaulPayment();
            final var account = AccountMockBuilder.mockDefaultValues();

            when(repository.save(any())).thenReturn(payment);
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(operation));
            when(repository.findAllNotPaidByAccountId(any())).thenReturn(purchaseNotPaid);

            final var result = service.create(payment);
            assertEquals(payment, result);
        }

        @Test
        void shouldReturnAValidTransactionWhenWeHaveTwoTransactions() {
            final var payment = TransactionMockBuilder.mockDefaultPayment();
            final var transactionsNotPaid = List.of(
                    TransactionMockBuilder.mockDefaultValues(),
                    TransactionMockBuilder.mockDefaultInstallment()
            );
            final var operation = OperationTypeMockBuilder.mockDefaulPayment();
            final var account = AccountMockBuilder.mockDefaultValues();

            when(repository.save(any())).thenReturn(payment);
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(operation));
            when(repository.findAllNotPaidByAccountId(any())).thenReturn(transactionsNotPaid);

            final var result = service.create(payment);
            assertEquals(payment, result);
        }

        @Test
        void shouldThrowsAnExceptionIfTheBalanceIsGreaterThanZero() {
            final var payment = TransactionMockBuilder.mockDefaultPayment();
            payment.setAmount(BigDecimal.valueOf(300));
            final var transactionsNotPaid = List.of(
                    TransactionMockBuilder.mockDefaultValues(),
                    TransactionMockBuilder.mockDefaultInstallment()
            );
            final var operation = OperationTypeMockBuilder.mockDefaulPayment();
            final var account = AccountMockBuilder.mockDefaultValues();

            when(repository.save(any())).thenReturn(payment);
            when(accountService.findById(any())).thenReturn(Optional.of(account));
            when(operationTypeService.findById(any())).thenReturn(Optional.of(operation));
            when(repository.findAllNotPaidByAccountId(any())).thenReturn(transactionsNotPaid);

            assertThrows(OverpaymentException.class, () -> service.create(payment));
        }
    }
}
