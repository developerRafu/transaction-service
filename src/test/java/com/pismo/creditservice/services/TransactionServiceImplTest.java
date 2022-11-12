package com.pismo.creditservice.services;

import com.pismo.creditservice.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TransactionServiceImplTest {
    TransactionRepository repository;
    ITransactionService service;
    IAccountService accountService;

    @BeforeEach
    void setUp() {
        repository = mock(TransactionRepository.class);
        accountService = mock(IAccountService.class);
        service = new TransactionServiceImpl(repository, accountService);
    }
}