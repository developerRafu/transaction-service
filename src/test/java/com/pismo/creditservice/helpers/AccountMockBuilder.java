package com.pismo.creditservice.helpers;

import com.pismo.creditservice.domain.Account;

public class AccountMockBuilder {
    private AccountMockBuilder() {}
    public static Account mockDefaultValues() {
        return Account
                .builder()
                .id(MockConstants.MOCKED_ID)
                .documentNumber(MockConstants.MOCKED_DOCUMENT_NUMBER)
                .build();
    }
}
