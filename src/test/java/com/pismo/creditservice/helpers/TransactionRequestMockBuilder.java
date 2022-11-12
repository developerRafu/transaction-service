package com.pismo.creditservice.helpers;

import com.pismo.creditservice.domain.enums.OperationType;
import com.pismo.creditservice.vo.requests.TransactionRequest;

public class TransactionRequestMockBuilder {
    private TransactionRequestMockBuilder(){}
    public static TransactionRequest mockDefaultValues(){
        return TransactionRequest
                .builder()
                .accountId(MockConstants.MOCKED_ID)
                .amount(MockConstants.MOCKED_AMOUNT)
                .operationTypeId(OperationType.PAYMENT.getId())
                .build();
    }
}
