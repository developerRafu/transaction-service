package com.pismo.creditservice.errors.helpers;

import lombok.Getter;

@Getter
public enum MessagesEnum {
    INVALID_NUMBER_DOCUMENT("Invalid document number for s%"),
    ACCOUNT_NOT_FOUND("Account not found for s%"),
    INVALID_TRANSACTION_TYPE("INVALID_TRANSACTION_TYPE");
    private final String message;

    MessagesEnum(final String message) {
        this.message = message;
    }

    public String getFormattedMessage(final Object... objects) {
        return String.format(message, objects);
    }
}
