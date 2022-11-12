package com.pismo.creditservice.helpers;

import com.pismo.creditservice.vo.requests.AccountRequest;

public class AccountRequestMockBuilder {
    private AccountRequestMockBuilder() {
    }

    public static AccountRequest mockDefaultValues() {
        return AccountRequest.builder().documentNumber(MockConstants.MOCKED_DOCUMENT_NUMBER).build();
    }
}
