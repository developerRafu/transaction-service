package com.rafu.creditservice.helpers;

import com.rafu.creditservice.domain.OperationType;
import com.rafu.creditservice.domain.enums.OperationTypeEnum;

public class OperationTypeMockBuilder {
    private OperationTypeMockBuilder() {
    }

    public static OperationType mockDefaultValues() {
        return OperationType.builder().id(MockConstants.MOCKED_ID).description(OperationTypeEnum.PURCHASE).build();
    }
}
