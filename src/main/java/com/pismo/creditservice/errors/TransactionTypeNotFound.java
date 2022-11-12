package com.pismo.creditservice.errors;

import lombok.Getter;

@Getter
public class TransactionTypeNotFound extends RuntimeException {
    private final Long transactionTypeId;

    public TransactionTypeNotFound(final Long transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }
}
