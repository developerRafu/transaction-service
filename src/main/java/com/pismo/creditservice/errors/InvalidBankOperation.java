package com.pismo.creditservice.errors;

import com.pismo.creditservice.domain.enums.OperationTypeEnum;

public class InvalidBankOperation extends RuntimeException {
    final OperationTypeEnum operationTypeEnum;

    public InvalidBankOperation(OperationTypeEnum operationTypeEnum) {
        this.operationTypeEnum = operationTypeEnum;
    }
}
