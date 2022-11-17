package com.rafu.creditservice.utils.models;

import com.rafu.creditservice.domain.enums.OperationTypeEnum;
import lombok.*;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankStatement {
    private BigDecimal amount;
    private OperationTypeEnum operationTypeEnum;

    public boolean isValid(){
        if (isAmountOut()) {
            return !isGreaterThanZero();
        } else {
            return isGreaterThanZero();
        }
    }

    public boolean isAmountOut() {
        return operationTypeEnum == OperationTypeEnum.PURCHASE
                || operationTypeEnum == OperationTypeEnum.INSTALLMENT_PURCHASE
                || operationTypeEnum == OperationTypeEnum.WITHDRAWAL;
    }

    private boolean isGreaterThanZero() {
        return ObjectUtils.compare(amount, BigDecimal.ZERO) > 0;
    }
}
