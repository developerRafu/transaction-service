package com.rafu.creditservice.helpers;

import com.rafu.creditservice.domain.OperationType;
import com.rafu.creditservice.domain.enums.OperationTypeEnum;

public class OperationTypeMockBuilder {
    private OperationTypeMockBuilder() {
    }

    public static OperationType mockDefaultValues() {
        return OperationType
                .builder()
                .id(MockConstants.MOCKED_ID)
                .description(OperationTypeEnum.PURCHASE)
                .build();
    }

    public static OperationType mockDefaulPayment() {
        return OperationType
                .builder()
                .id(MockConstants.MOCKED_ID)
                .description(OperationTypeEnum.PAYMENT)
                .build();
    }

    public static OperationType mockDefaultInstalment() {
        return OperationType
                .builder()
                .id(MockConstants.MOCKED_ID)
                .description(OperationTypeEnum.INSTALLMENT_PURCHASE)
                .build();
    }
}
