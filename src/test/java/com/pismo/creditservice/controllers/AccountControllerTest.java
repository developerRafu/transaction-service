package com.pismo.creditservice.controllers;

import com.pismo.creditservice.errors.AccountNotFoundException;
import com.pismo.creditservice.helpers.AccountMockBuilder;
import com.pismo.creditservice.helpers.MockConstants;
import com.pismo.creditservice.mappers.AccountMapper;
import com.pismo.creditservice.services.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountControllerTest {
    AccountController controller;
    IAccountService service;
    AccountMapper mapper;

    @BeforeEach
    void setUp() {
        service = mock(IAccountService.class);
        mapper = spy(Mappers.getMapperClass(AccountMapper.class));
        controller = new AccountController(service, mapper);
    }

    @Nested
    class getByIdTests {
        @Test
        void shouldReturnAOkResponse() {
            final var account = AccountMockBuilder.mockDefaultValues();
            when(service.findById(any())).thenReturn(Optional.of(account));
            final var result = controller.getById(MockConstants.MOCKED_ID);
            assertEquals(HttpStatus.OK, result.getStatusCode());
            assertNotNull(result.getBody());
            assertEquals(account.getId(), result.getBody().getAccountId());
            assertEquals(account.getDocumentNumber(), result.getBody().getDocumentNumber());
        }

        @Test
        void shouldReturnAExceptionWhenAccountIsNull() {
            when(service.findById(any())).thenReturn(Optional.empty());
            assertThrows(AccountNotFoundException.class, () -> controller.getById(MockConstants.MOCKED_ID));

        }
    }
}
