package com.pismo.creditservice.helpers;

import com.pismo.creditservice.domain.OperationType;
import com.pismo.creditservice.domain.Transaction;
import com.pismo.creditservice.domain.enums.OperationTypeEnum;

import java.time.LocalDateTime;

public class TransactionMockBuilder {
    private TransactionMockBuilder() {
    }

    public static Transaction mockDefaultValues() {
        return Transaction
                .builder()
                .id(MockConstants.MOCKED_ID)
                .account(AccountMockBuilder.mockDefaultValues())
                .amount(MockConstants.MOCKED_AMOUNT)
                .eventDate(LocalDateTime.now())
                .operationType(OperationTypeMockBuilder.mockDefaultValues())
                .build();
    }
}
