package com.pismo.creditservice.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum OperationTypeEnum {
    PURCHASE(1L),
    INSTALLMENT_PURCHASE(2L),
    WITHDRAWAL(3L),
    PAYMENT(4L);
    private final Long id;

    OperationTypeEnum(final Long id) {
        this.id = id;
    }

    public static OperationTypeEnum of(final Integer id) {
        return Arrays
                .stream(OperationTypeEnum.values())
                .filter(type -> Objects.equals(type.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
