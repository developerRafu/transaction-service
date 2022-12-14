package com.rafu.creditservice.helpers;

import java.math.BigDecimal;

public class MockConstants {

    private MockConstants() {}
    public final static Long MOCKED_ID = 1L;
    public final static String MOCKED_DOCUMENT_NUMBER = "12345678900";
    public static final BigDecimal MOCKED_AMOUNT = BigDecimal.valueOf(100);
    public static final BigDecimal MOCKED_AMOUNT_2 = BigDecimal.valueOf(150);
    public static final BigDecimal MOCKED_NEGATIVE_AMOUNT = BigDecimal.valueOf(-100);
}
