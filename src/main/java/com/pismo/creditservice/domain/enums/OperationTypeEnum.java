package com.pismo.creditservice.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum OperationTypeEnum {
    PURCHASE(1L, "Purchase", "negative"),
    INSTALLMENT_PURCHASE(2L, "Installment purchase", "negative"),
    WITHDRAWAL(3L, "Withdrawal", "negative"),
    PAYMENT(4L, "Payment", "positive");
    private final Long id;
    private final String operationAmountDescription;
    private final String description;

    OperationTypeEnum(final Long id, String description, String operationAmountDescription) {
        this.id = id;
        this.operationAmountDescription = operationAmountDescription;
        this.description = description;
    }

    public static OperationTypeEnum of(final Long id) {
        return Arrays
                .stream(OperationTypeEnum.values())
                .filter(type -> Objects.equals(type.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
