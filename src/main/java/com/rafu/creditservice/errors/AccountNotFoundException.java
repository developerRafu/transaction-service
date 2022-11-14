package com.rafu.creditservice.errors;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {
    private final Long accountId;

    public AccountNotFoundException(final Long accountId) {
        this.accountId = accountId;
    }
}
