package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.enums.OperationTypeEnum;
import com.pismo.creditservice.errors.AccountNotFoundException;
import com.pismo.creditservice.errors.InvalidBankOperation;
import com.pismo.creditservice.errors.TransactionTypeNotFound;
import com.pismo.creditservice.helpers.AccountMockBuilder;
import com.pismo.creditservice.helpers.OperationTypeMockBuilder;
import com.pismo.creditservice.helpers.TransactionMockBuilder;
import com.pismo.creditservice.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    }
}
