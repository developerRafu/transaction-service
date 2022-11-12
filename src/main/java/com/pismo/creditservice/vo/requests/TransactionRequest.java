package com.pismo.creditservice.vo.requests;

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
public class TransactionRequest {
    @JsonProperty(value = "account_id")
    private Long accountId;
    @JsonProperty(value = "operation_type_id")
    private Integer operationTypeId;
    private BigDecimal amount;
}
