package com.pismo.creditservice.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum OperationType {
    PURCHASE(1),
    INSTALLMENT_PURCHASE(2),
    WITHDRAWAL(3),
    PAYMENT(4);
    private final Integer id;

    OperationType(final Integer id) {
        this.id = id;
    }

    public static OperationType of(final Integer id) {
        return Arrays
                .stream(OperationType.values())
                .filter(type -> Objects.equals(type.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
