package com.rafu.creditservice.helpers;

import com.rafu.creditservice.domain.enums.OperationTypeEnum;
import com.rafu.creditservice.vo.requests.TransactionRequest;

public class TransactionRequestMockBuilder {
    private TransactionRequestMockBuilder(){}
    public static TransactionRequest mockDefaultValues(){
        return TransactionRequest
                .builder()
                .accountId(MockConstants.MOCKED_ID)
                .amount(MockConstants.MOCKED_AMOUNT)
                .operationTypeId(OperationTypeEnum.PAYMENT.getId())
                .build();
    }
}
