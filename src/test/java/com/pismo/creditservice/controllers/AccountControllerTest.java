package com.pismo.creditservice.controllers;

import com.pismo.creditservice.mappers.AccountMapper;
import com.pismo.creditservice.services.IAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.mapstruct.factory.Mappers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

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
}