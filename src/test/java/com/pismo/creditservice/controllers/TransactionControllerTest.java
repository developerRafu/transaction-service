package com.pismo.creditservice.controllers;

import com.pismo.creditservice.domain.Transaction;
import com.pismo.creditservice.errors.AccountNotFoundException;
import com.pismo.creditservice.errors.TransactionTypeNotFound;
import com.pismo.creditservice.helpers.TransactionMockBuilder;
import com.pismo.creditservice.helpers.TransactionRequestMockBuilder;
import com.pismo.creditservice.mappers.TransactionMapper;
import com.pismo.creditservice.services.ITransactionService;
import com.pismo.creditservice.vo.responses.TransactionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class TransactionControllerTest {
    TransactionController controller;
    ITransactionService service;
    TransactionMapper mapper;

    @BeforeEach
    void setUp() {
        service = mock(ITransactionService.class);
        mapper = spy(Mappers.getMapperClass(TransactionMapper.class));
        controller = new TransactionController(service, mapper);
    }

    @Nested
    class postTests {
        @Test
        void shouldReturnAValidOkResponse() {
            final var request = TransactionRequestMockBuilder.mockDefaultValues();
            final var transaction = TransactionMockBuilder.mockDefaultValues();
            when(service.create(any())).thenReturn(transaction);
            final var result = controller.post(request);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertNotNull(result.getBody());
            assertTransaction(transaction, result.getBody());
        }

        @Test
        void shouldReturnAExceptionWhenServiceThrowsAccountNotFoundException() {
            final var request = TransactionRequestMockBuilder.mockDefaultValues();
            when(service.create(any())).thenThrow(AccountNotFoundException.class);
            assertThrows(AccountNotFoundException.class, () -> controller.post(request));
        }

        @Test
        void shouldReturnAExceptionWhenServiceThrowsTransactionTypeNotFound() {
            final var request = TransactionRequestMockBuilder.mockDefaultValues();
            when(service.create(any())).thenThrow(TransactionTypeNotFound.class);
            assertThrows(TransactionTypeNotFound.class, () -> controller.post(request));
        }
    }

    private void assertTransaction(final Transaction expected, final TransactionResponse current) {
        assertEquals(expected.getAmount(), current.getAmount());
        assertEquals(expected.getOperationType().getId(), current.getOperationTypeId());
        assertEquals(expected.getAccount().getId(), current.getAccountId());
        assertEquals(expected.getId(), current.getTransactionId());
    }
}