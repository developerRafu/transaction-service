package com.rafu.creditservice.errors;

import com.rafu.creditservice.domain.enums.OperationTypeEnum;

public class InvalidBankOperation extends RuntimeException {
    final OperationTypeEnum operationTypeEnum;

    public InvalidBankOperation(OperationTypeEnum operationTypeEnum) {
        this.operationTypeEnum = operationTypeEnum;
    }
}
