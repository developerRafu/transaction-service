package com.pismo.creditservice.helpers;

import com.pismo.creditservice.domain.OperationType;
import com.pismo.creditservice.domain.enums.OperationTypeEnum;

public class OperationTypeMockBuilder {
    private OperationTypeMockBuilder() {
    }

    public static OperationType mockDefaultValues() {
        return OperationType.builder().id(MockConstants.MOCKED_ID).description(OperationTypeEnum.PURCHASE).build();
    }
}
