package com.rafu.creditservice.helpers;

import com.rafu.creditservice.domain.Transaction;

import java.time.LocalDateTime;

public class TransactionMockBuilder {
    private TransactionMockBuilder() {
    }

    public static Transaction mockDefaultValues() {
        return Transaction
                .builder()
                .id(MockConstants.MOCKED_ID)
                .account(AccountMockBuilder.mockDefaultValues())
                .amount(MockConstants.MOCKED_NEGATIVE_AMOUNT)
                .eventDate(LocalDateTime.now())
                .operationType(OperationTypeMockBuilder.mockDefaultValues())
                .build();
    }

    public static Transaction mockDefaultPayment() {
        return Transaction
                .builder()
                .id(MockConstants.MOCKED_ID)
                .account(AccountMockBuilder.mockDefaultValues())
                .amount(MockConstants.MOCKED_AMOUNT)
                .eventDate(LocalDateTime.now())
                .operationType(OperationTypeMockBuilder.mockDefaultValues())
                .build();
    }

    public static Transaction mockDefaultInstallment() {
       return Transaction
                .builder()
                .id(MockConstants.MOCKED_ID)
                .account(AccountMockBuilder.mockDefaultValues())
                .amount(MockConstants.MOCKED_NEGATIVE_AMOUNT)
                .eventDate(LocalDateTime.now())
                .operationType(OperationTypeMockBuilder.mockDefaultInstalment())
                .build();
    }
}
