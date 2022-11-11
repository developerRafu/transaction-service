package com.pismo.creditservice.services;

import com.pismo.creditservice.domain.Account;

public interface IAccountService {
Account create(final String documentNumber);
}
