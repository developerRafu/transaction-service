package com.pismo.creditservice.errors.helpers;

import lombok.Getter;

@Getter
public enum MessagesEnum {
    INVALID_NUMBER_DOCUMENT("Invalid document number for s%");
    private final String message;

    MessagesEnum(final String message) {
        this.message = message;
    }
    public String getFormattedMessage(final Object... objects){
        return String.format(message, objects);
    }
}
