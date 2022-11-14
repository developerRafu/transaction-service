package com.pismo.creditservice.vo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    @JsonProperty(value = "account_id")
    @NotNull
    private Long accountId;
    @JsonProperty(value = "operation_type_id")
    @NotNull
    private Long operationTypeId;
    @NotNull
    private BigDecimal amount;
}
