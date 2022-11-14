package com.rafu.creditservice.services;

import com.rafu.creditservice.helpers.AccountMockBuilder;
import com.rafu.creditservice.helpers.MockConstants;
import com.rafu.creditservice.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {
    IAccountService service;
    AccountRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(AccountRepository.class);
        service = new AccountServiceImpl(repository);
    }

    @Nested
    class createMethodTests {
        @Test
        void shouldCallRepository() {
            final var account = AccountMockBuilder.mockDefaultValues();
            when(repository.save(any())).thenReturn(account);
            final var result = service.create(MockConstants.MOCKED_DOCUMENT_NUMBER);
            assertEquals(account, result);
            verify(repository).save(any());
        }
    }

    @Nested
    class findByIdTests {
        @Test
        void shouldFindAccount() {
            final var account = AccountMockBuilder.mockDefaultValues();
            when(repository.findById(any())).thenReturn(Optional.of(account));
            final var result = service.findById(MockConstants.MOCKED_ID);
            assertTrue(result.isPresent());
            assertEquals(account, result.get());
        }
    }
}
