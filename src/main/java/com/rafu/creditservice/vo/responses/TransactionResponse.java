package com.rafu.creditservice.vo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
    @JsonProperty(value = "transaction_id")
    private Long transactionId;
    @JsonProperty(value = "account_id")
    private Long accountId;
    @JsonProperty(value = "operation_type_id")
    private Long operationTypeId;
    private BigDecimal amount;

}
